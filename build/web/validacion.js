var opcionSeleccionada = ""; // Variable global para almacenar la opción seleccionada

function setOpcion(opcion) {
    opcionSeleccionada = opcion;
}

function validarFormulario() {

    // Validación para "Alta" o "Edicion": asegurar que todos los campos estén llenos
    if (opcionSeleccionada === "Alta" || opcionSeleccionada === "Edicion") {
        var idArticulo = document.forms["myForm"]["identi"].value;
        var categoria = document.forms["myForm"]["catego"].value;
        var descripcion = document.forms["myForm"]["descri"].value;
        var precio = document.forms["myForm"]["precio"].value;

        if (idArticulo === "" || categoria === "" || descripcion === "" || precio === "") {
            alert("Por favor, complete todos los campos.");
            return false; // Evitar envío del formulario
        }
    }

    // Validación para "Baja": asegurar que el campo "identi" esté lleno
    if (opcionSeleccionada === "Baja") {
        var idArticulo = document.forms["myForm"]["identi"].value;

        if (idArticulo === "") {
            alert("Por favor, complete el campo 'Id. Artículo'.");
            return false; // Evitar envío del formulario
        }
    }

    return true; // Permitir envío del formulario si no hay problemas de validación
}
