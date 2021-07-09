package com.pradyanti_1313617004.beautyholic.Model;

import com.pradyanti_1313617004.beautyholic.R;

import java.util.ArrayList;

public class ProductTypeData {

    private static String[] productTypeNames = {
            "Blush",
            "Bronzer",
            "Eyebrow",
            "Eyeliner",
            "Eyeshadow",
            "Foundation",
            "Lip Liner",
            "Lipstick",
            "Mascara",
            "Nail Polish"
    };

    private static String[] productTypeDetails = {
            "Blush (called rouge, blusher or blush in English) is a cosmetic commonly used by women to redden the cheeks to give a more youthful appearance and to define the shape of the cheekbones.",
            "Bronzer is useful for giving a natural warm effect on the face. So, when exposed to sunlight, your face will look glowing, healthy, and exotic.",
            "Eyebrows are one of the makeup products that you must have to style and color your eyebrows. Eyebrow has a variety of colors that are close to the natural eyebrow color and has a variety of shapes, such as eyebrow pencil, eyebrow powder, eyebrow gel, eyebrow mascara, and eyebrow tint.",
            "Eyeliner to make a line at the top edge of the eye. The goal is to beautify the appearance of the eyes so that the eyes become sharper.",
            "Eye shadow is a cosmetic that is used on the eyelids and under the eyebrows with the aim of giving color to make the eyes look more alive. Eyeshadows has a variety of shapes, such as palettes, creams or pencils.",
            "Foundation serves to disguise or cover parts of the face that are less than perfect. Such as acne scars, skin that is less smooth, or uneven skin tone.",
            "Lip liner is used to frame the lips, smooth out asymmetrical lips, and define lips to make them look thicker. The use of lip liner will also keep the lipstick application neat so it doesn't easily come out of the lip line.",
            "Lipstick is used to give color to the lips. Lipstick is packaged in solid sticks (sticks), pencils, palettes, crayons, and some are packaged in bottles with a liquid texture. Lipsticks have textures that vary from creamy, satin, to matte.",
            "Mascara is a cosmetic product that is generally used to beautify the eyes. Its functions include darkening, thickening, lengthening, curling and/or clarifying eyelashes.",
            "Nail polish is a special soft paint that is used to color the top layer of the nail. Nail polish is available in various tones and colors."
    };

    private static int[] productTypeImage = {
            R.drawable.blush,
            R.drawable.bronzer,
            R.drawable.eyebrow,
            R.drawable.eyeliner,
            R.drawable.eyeshadow,
            R.drawable.foundation,
            R.drawable.lips_liner,
            R.drawable.lipstick,
            R.drawable.mascara,
            R.drawable.nail_polish
    };

    public static ArrayList<ProductType> getListData() {
        ArrayList<ProductType> list = new ArrayList<>();
        for (int position = 0; position < productTypeNames.length; position++ ) {
            ProductType productType = new ProductType();
            productType.setName(productTypeNames[position]);
            productType.setPhotoPoster(productTypeImage[position]);
            productType.setDescription(productTypeDetails[position]);
            list.add(productType);
        }
        return list;
    }
}
