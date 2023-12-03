# Vinilos Android App

Proyecto en Kotlin para el front-end de la aplicación Vinilos

### Ubicación del APK
El APK generado se puede encontrar en la carpeta app/release

### Generación del APK
Para generar el apk a partir del código se deben seguir los siguientes pasos:

* Abrir el proyecto en Android Studio
* Dar click en Build -> Generate Signed Bundle/APK
* En la ventana emergente seleccionar APK
* Dar click en Next
* En "key store path", seleccionar la ruta del archivo keystore.jks que se encuentra la carpeta raiz del repositorio
* En "key store password" y "key password" escribir "123456"
* En "key alias" poner key0, o cualquier otro nombre
* Alternativamente, se puede dar click en "Create New" y crear una nueva llave.
* Dar click en Next
* Seleccionar la carpeta en la que se quiere guardar el apk y la opcion release
* Dar click en Create 

### Executing program

* How to run the program
* Step-by-step bullets
```
code blocks for commands
```

## Help

Any advise for common problems or issues.
```
command to run if program contains helper info
```

## Run test in expresso

For run test in espresso is recommend remove animations in the device of test, you must open settings on the device and search "Remove animations" for enable this option, as is indicated in the next image:

<img width="175" alt="Eliminar animaciones para test" src="https://github.com/santiagomd11/Vinilos-Mobile/assets/123957494/6da26439-6044-4e46-9da7-b0fe64ba985d">


## Authors

Contributors names and contact info

ex. Dominique Pizzie  
ex. [@DomPizzie](https://twitter.com/dompizzie)

## Version History

* 0.2
    * Various bug fixes and optimizations
    * See [commit change]() or See [release history]()
* 0.1
    * Initial Release

## License

This project is licensed under the [NAME HERE] License - see the LICENSE.md file for details

## Acknowledgments

Inspiration, code snippets, etc.
* [awesome-readme](https://github.com/matiassingers/awesome-readme)
* [PurpleBooth](https://gist.github.com/PurpleBooth/109311bb0361f32d87a2)
* [dbader](https://github.com/dbader/readme-template)
* [zenorocha](https://gist.github.com/zenorocha/4526327)
* [fvcproductions](https://gist.github.com/fvcproductions/1bfc2d4aecb01a834b46)
