#!/usr/bin/env python3
"""
Palette remap with transparency using index 0, preserving all colours by shifting.
Also supports optional overlays.
"""

from pathlib import Path
from PIL import Image
import sys

# -------- paths --------------------------------------------------------------

ROOT_DIR     = Path(__file__).resolve().parent
PALETTE_DIR  = ROOT_DIR / "palettes"
ORIGINAL_DIR = ROOT_DIR / "originals"
OVERLAY_DIR  = ROOT_DIR / "overlays"
OUTPUT_ROOT  = ROOT_DIR / "remapped"

# -------- helpers ------------------------------------------------------------

def shifted_palette_image(png: Path) -> Image.Image:
    """Load palette PNG and shift all colours up by 1 index. Index 0 is reserved for transparency."""
    rgb = Image.open(png).convert("RGB")
    colours = []
    for px in rgb.getdata():
        if px not in colours:
            colours.append(px)
        if len(colours) == 255:  # leave room for transparency at index 0
            break

    # Create shifted palette: index 0 = transparent (0,0,0), others shifted
    shifted = [0, 0, 0]  # index 0 = transparent
    shifted.extend([c for rgb in colours for c in rgb])

    # Pad to 768 bytes
    shifted += [0] * (768 - len(shifted))

    pal = Image.new("P", (1, 1))
    pal.putpalette(shifted)
    return pal


def quantize_with_transparency(rgba: Image.Image, pal_img: Image.Image) -> Image.Image:
    """
    Quantize to a pre-shifted palette that reserves index 0 for transparency.
    Transparent pixels get index 0, everything else is quantized to index 1+.
    """
    rgb = rgba.convert("RGB")
    quantized = rgb.quantize(palette=pal_img, dither=Image.NONE)

    # Make fully transparent pixels become index 0
    alpha = rgba.getchannel("A")
    mask = alpha.point(lambda a: 255 if a == 0 else 0, mode="1")
    if mask.getbbox():  # only apply if there are transparent pixels
        quantized.paste(0, None, mask)
        quantized.info["transparency"] = bytes([0])

    return quantized

# -------- main ---------------------------------------------------------------

def main():
    for p in (PALETTE_DIR, ORIGINAL_DIR):
        if not p.is_dir():
            sys.exit(f"Required directory missing: {p}")
    OUTPUT_ROOT.mkdir(exist_ok=True)

    for pal_png in sorted(PALETTE_DIR.glob("*.png")):
        pal_name = pal_png.stem
        dest_dir = OUTPUT_ROOT / pal_name
        dest_dir.mkdir(exist_ok=True)
        print(f"[+] Palette: {pal_name}")

        palette_img = shifted_palette_image(pal_png)

        for orig_png in sorted(ORIGINAL_DIR.glob("*.png")):
            # Load base image
            base = Image.open(orig_png).convert("RGBA")

            # Apply overlay if it exists
            overlay_path = OVERLAY_DIR / orig_png.name
            if overlay_path.is_file():
                overlay = Image.open(overlay_path).convert("RGBA")
                base = Image.alpha_composite(base, overlay)

            # Remap colours and preserve transparency
            indexed = quantize_with_transparency(base, palette_img)

            out_path = dest_dir / orig_png.name
            indexed.save(out_path, format="PNG")
            print(f"    saved {out_path.relative_to(ROOT_DIR)}")

    print("\nAll done. Output is in:", OUTPUT_ROOT)


if __name__ == "__main__":
    main()
