function handleSubmit() {
    var input = document.getElementById('input').value;
    var password = document.getElementById('password').value;
    let data = ""
    if (input.indexOf('@') >= 0) {
        data = {
            "email": input,
            "password": password
        }
    }
    else {
        data = {
            "username": input,
            "password": password
        }
    }

    fetch('/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
    .then(response => response.json())
    .then(data => console.log(data));
//    .then(window.location.href = 'category.html');

    window.onload = function() {
        const button = document.getElementById('bu');
        button.addEventListener('click', handleSubmit);
    }
}

