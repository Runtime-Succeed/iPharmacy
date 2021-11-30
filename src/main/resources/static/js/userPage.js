function getUsername() {
    if (localStorage.getItem("username") === "" || localStorage.getItem("username") == null) {
//        console.log(localStorage.getItem("username"));
        document.getElementById("signIn").style.display = "block";
        document.getElementById("user").innerHTML = "";
        document.getElementById("user").style.display = "none";
        document.getElementById("logOut").style.display = "none";
    }
    else {
        document.getElementById("signIn").style.display = "none";
        document.getElementById("user").innerHTML = "Welcome, " + localStorage.getItem("username");
        document.getElementById("user").style.display = "block";
        document.getElementById("logOut").style.display = "block";
    }
}


function logout() {
    localStorage.setItem("username", "");
    fetch ('/logout', {
        method: "POST",
        'Content-Type': 'application/json',
    })
    window.location.href = 'index.html';
}


async function uploadFile() {
        let formData = new FormData();
        formData.append("file", fileupload.files[0]);
        await fetch('/upload', {
            method: "POST",
            body: formData
        });
        alert("File uploaded!");
}