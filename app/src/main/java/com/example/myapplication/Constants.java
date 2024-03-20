package com.example.myapplication;

import com.example.myapplication.Question;

public class Constants {
    public static Question[] questions = {
            new Question("¿Son los koalas osos o marsupiales?",
                    new String[]{"Osos", "Marsupiales", "Ambos", "Ninguno"},
                    "Marsupiales",
                    "https://images.photowall.com/products/60857/koala-with-baby.jpg?h=699&q=85"),
            new Question("¿Por qué son diferentes estos koalas?",
                    new String[]{"No se sabe", "Uno es macho y otro es hembra",
                            "Por el clima de cada región", "Uno es mayor que el otro"},
                    "Por el clima de cada región",
                    "https://i.ibb.co/wzNHz6G/Sin-t-tulo.png"),
            new Question("¿Cuál de estos es un dimorfismo sexual en los koalas?",
                    new String[]{"El macho tiene una glándula odorífera", "La hembra tiene orejas puntiagudas",
                            "El macho tienen un dedo más en sus manos", "La hembra tiene una pequeña cola que el macho no"},
                    "El macho tiene una glándula odorífera",
                    "https://i.ibb.co/jLFcvDs/Sin-t-tulo.png"),
            new Question("¿Qué caracteriza a las manos de los koalas?",
                    new String[]{"Tienen 4 dedos", "Sólo dos dedos tienen zarpas",
                            "Son idénticas a sus sus pies", "Tienen dos pulares"},
                    "Tienen dos pulares",
                    "https://media.istockphoto.com/id/1287297928/photo/koalas-paw-close-up.jpg?s=612x612&w=0&k=20&c=W9ihEIC_l2N9I6Ogyq1Rmuz-qPWwsbe-gCUtEmNmA-w="),
            new Question("¿Cómo se pesa a los koalas bebés?",
                    new String[]{"Se colocan en una báscula", "Se pesan junto a un peluche, luego se pesa el peluche solo y se hace la diferencia",
                            "Se pesan a aproximadamente sujetándolos en brazos", "No se pueden pesar"},
                    "Se pesan junto a un peluche, luego se pesa el peluche solo y se hace la diferencia",
                    "https://pbs.twimg.com/media/DoCuodEXUAAZg1H.jpg:large"),
    };
}
