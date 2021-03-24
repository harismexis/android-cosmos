package com.harismexis.cosmos.mrp.model.enums

enum class CameraIdentifier(val identifier: String) {
    FHAZ("fhaz"), // Front Hazard Avoidance Camera
    RHAZ("rhaz"), // Rear Hazard Avoidance Camera
    MAST("mast"), // Mast Camera
    CHEMCAM("chemcam"), // Chemistry and Camera Complex
    MAHLI("mahli"),    // Mars Hand Lens Imager
    MARDI("mardi"),    // Mars Descent Imager
    NAVCAM("navcam"), // Navigation Camera
    PANCAM("pancam"), // Panoramic Camera
    MINITES("minites"), // Miniature Thermal Emission Spectrometer (Mini-TES)
}