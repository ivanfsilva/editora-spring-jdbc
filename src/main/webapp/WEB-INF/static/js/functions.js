function localizarPorCargo() {
    var id = document.getElementById('cargo');
    var value = id.options[id.selectedIndex].value;

    if (value == '') {
        window.location = 'http://localhost:8080/editora_war/funcionario/add';
    } else {
        window.location = 'http://localhost:8080/editora_war/funcionario/find/cargo/' + value;
    }
}

function localizarPorNome() {
    var value = document.getElementById('nome').value;

    if (value == '') {
        window.location = 'http://localhost:8080/editora_war/funcionario/add';
    } else {
        window.location = 'http://localhost:8080/editora_war/funcionario/find/nome/' + value;
    }
}