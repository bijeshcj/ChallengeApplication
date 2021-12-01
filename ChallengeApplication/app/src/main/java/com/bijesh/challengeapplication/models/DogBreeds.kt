package com.bijesh.challengeapplication.models

data class DogBreeds(
    val bred_for: String,
    val breed_group: String,
    val height: Height,
    val id: Int,
    val image: Image,
    val life_span: String,
    val name: String,
    val origin: String,
    val reference_image_id: String,
    val temperament: String,
    val weight: Weight
)