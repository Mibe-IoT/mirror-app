package com.mibe.bm.app.panel

enum class AppPanelType(
    val asciiArt: String,
) {
    NEWS(
        """ooooo       oooo                                    
`888b.      `88'                                    
 88 `88b.    88   .ooooo.  oooo oooo    ooo  .oooo.o
 88   `88b.  88  d88' `88b  `88. `88.  .8'  d88(  "8
 88     `88b.88  888ooo888   `88..]88..8'   `"Y88b. 
 88       `8888  888    .o    `888'`888'    o.  )88b
o88o        `88  `Y8bod8P'     `8'  `8'     8""888P'"""
    ),
    WEATHER(
        """oooooo   oooooo     oooo                         .   oooo                          
 `888.    `888.     .8'                        .o8   `888                          
  `888.   .8888.   .8'    .ooooo.   .oooo.   .o888oo  888 .oo.    .ooooo.  oooo d8b
   `888  .8'`888. .8'    d88' `88b `P  )88b    888    888P"Y88b  d88' `88b `888""8P
    `888.8'  `888.8'     888ooo888  .oP"888    888    888   888  888ooo888  888    
     `888'    `888'      888    .o d8(  888    888 .  888   888  888    .o  888    
      `8'      `8'       `Y8bod8P' `Y888""8o   "888" o888o o888o `Y8bod8P' d888b   """
    )

}