package com.bijesh.challengeapplication.models.desmodels

data class Breed(
    val bred_for: String,
    val breed_group: String,
    val description: String,
    val height: Height,
    val history: String,
    val id: Int,
    val life_span: String,
    val name: String,
    val reference_image_id: String,
    val temperament: String,
    val weight: Weight
)