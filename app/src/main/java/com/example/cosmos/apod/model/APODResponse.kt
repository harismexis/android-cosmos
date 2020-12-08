package com.example.cosmos.apod.model

class APODResponse(
    var date: String?, // eg. "2009-04-20"
    var explanation: String?, // eg. "When does Mars act like a liquid?  Although liquids freeze and evaporate quickly into the thin atmosphere of Mars, persistent winds may make large sand dunes appear to flow and even drip like a liquid.  Visible on the above image right are two flat top mesas in southern Mars, where the season is changing from Spring to Summer.  A light dome topped hill is also visible on the far left of the image.  As winds blow from right to left, flowing sand on and around the hills leaves picturesque streaks.  The dark arc-shaped droplets of fine sand are called barchans, and are the interplanetary cousins of similar Earth-based sand forms. Barchans can move intact downwind and can even appear to pass through each other. Over the past few weeks, winds on southern Mars have been kicking up dust and are being watched to see if they escalate into another of Mars' famous planet-scale sand storms.    digg_url = 'http://apod.nasa.gov/apod/ap090420.html'; digg_skin = 'compact';"
    var hdurl: String?, // eg. "https://apod.nasa.gov/apod/image/0904/sandmars_mro_big.jpg"
    var mediaType: String?, // eg. "image"
    var serviceVersion: String?, // eg. "v1"
    var title: String?, // eg. "Flowing Barchan Sand Dunes on Mars"
    var url: String? // "https://apod.nasa.gov/apod/image/0904/sandmars_mro.jpg"
)