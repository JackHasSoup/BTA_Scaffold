#!/usr/bin/env python3
"""
Index‑for‑index palette swap:

1. For every PNG in ./palette/ …
   • Create ./remapped/<palette‑stem>/.
   • For each PNG in ./original/:
       – Copy its *index map* intact.
       – Replace its .palette with the palette image’s .palette.
       – Convert result to RGBA (so downstream software doesn’t balk at 'P' mode).
       – Composite identically‑named overlay (if present in ./overlay/).
       – Save as PNG (RGBA).

Assumptions
-----------
• Both the original image and the palette image use 8‑bit indexed colour
  (mode 'P') or can be losslessly converted to it.
• They share the same palette **ordering** (index 0 means the same concept
  in both).  If a palette image defines <256 colours it is padded with black.
"""

from pathlib import Path
from PIL import Image

BASE      = Path(__file__).resolve().parent
ORIGINAL  = BASE / "original"
PALETTES  = BASE / "palette"
OVERLAYS  = BASE / "overlay"
REMAPPED  = BASE / "remapped"
REMAPPED.mkdir(exist_ok=True)

def ensure_mode_p(img: Image.Image) -> Image.Image:
    """Return image in mode 'P' with 256‑entry palette (pads with zeros)."""
    if img.mode == "P":
        # Ensure the palette has exactly 768 bytes (256×RGB)
        pal = img.getpalette()
        if pal is None:
            raise ValueError("Palette image has no palette data")
        if len(pal) < 768:
            pal += [0] * (768 - len(pal))
        img.putpalette(pal[:768])
        return img
    # Lossless conversion provided image has ≤256 colours
    return img.convert("P")

def swap_palette(index_img: Image.Image, new_pal_img: Image.Image) -> Image.Image:
    """Return a *new* 'P' image whose indices come from index_img but whose
       colour table comes from new_pal_img."""
    out = index_img.copy()
    out.putpalette(new_pal_img.getpalette()[:768])
    return out

def overlay_if_exists(base_rgba: Image.Image, overlay_path: Path) -> Image.Image:
    if overlay_path.exists():
        ov = Image.open(overlay_path).convert("RGBA")
        return Image.alpha_composite(base_rgba, ov)
    return base_rgba

def main():
    originals = sorted(ORIGINAL.glob("*.png"))
    if not originals:
        print("No PNGs in 'original'")
        return

    # Ensure originals are read once and cached as index maps
    originals_p = {p: ensure_mode_p(Image.open(p)) for p in originals}

    for pal_path in sorted(PALETTES.glob("*.png")):
        palette_name = pal_path.stem
        dst_dir = REMAPPED / palette_name
        dst_dir.mkdir(parents=True, exist_ok=True)

        pal_img = ensure_mode_p(Image.open(pal_path))
        print(f"→ Palette '{palette_name}'")

        for orig_path, idx_img in originals_p.items():
            out_p = swap_palette(idx_img, pal_img)

            # Convert to RGBA for saving (keeps any transparency index)
            out_rgba = out_p.convert("RGBA")

            # Overlay step
            out_rgba = overlay_if_exists(out_rgba, OVERLAYS / orig_path.name)

            out_rgba.save(dst_dir / orig_path.name, "PNG")
            print(f"   {orig_path.name} → {dst_dir.relative_to(BASE)}/")

if __name__ == "__main__":
    main()
