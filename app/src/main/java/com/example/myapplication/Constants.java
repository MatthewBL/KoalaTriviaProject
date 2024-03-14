package com.example.myapplication;

import com.example.myapplication.Question;

public class Constants {
    public static Question[] questions = {
            new Question("¿Son los koalas osos o marsupiales?",
                    new String[]{"Osos", "Marsupiales", "Ambos", "Ninguno"},
                    "Marsupiales",
                    1),
            new Question("¿Por qué son diferentes estos koalas?",
                    new String[]{"No se sabe", "Uno es macho y otro es hembra",
                            "Por el clima de cada región", "Uno es mayor que el otro"},
                    "Por el clima de cada región",
                    1),
            new Question("¿Cuál de estos es un dimorfismo sexual en los koalas?",
                    new String[]{"El macho tiene una glándula odorífera", "La hembra tiene orejas puntiagudas",
                            "El macho tienen un dedo más en sus manos", "La hembra tiene una pequeña cola que el macho no"},
                    "El macho tiene una glándula odorífera",
                    1),
            new Question("¿Qué caracteriza a las manos de los koalas?",
                    new String[]{"Tienen 4 dedos", "Sólo dos dedos tienen zarpas",
                            "Son idénticas a sus sus pies", "Tienen dos pulares"},
                    "Tienen dos pulares",
                    1),
            new Question("¿Cómo se pesa a los koalas bebés?",
                    new String[]{"Se colocan en una báscula", "Se pesan junto a un peluche, luego se pesa el peluche solo y se hace la diferencia",
                            "Se pesan a aproximadamente sujetándolos en brazos", "No se pueden pesar"},
                    "Se pesan junto a un peluche, luego se pesa el peluche solo y se hace la diferencia",
                    1),
    };
}
