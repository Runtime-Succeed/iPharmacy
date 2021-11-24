let title;
let username;

//window.addEventListener('load', function() {
//   if (localStorage.getItem("username") != "") {
//            console.log(localStorage.getItem("username"));
//            document.getElementById("signIn").style.visibility = "hidden";
//            document.getElementById("user").innerHTML = localStorage.getItem("username");
//            document.getElementById("user").style.visibility = "show";
//        }
//});

function getUsername() {
    if (localStorage.getItem("username") == "" || localStorage.getItem("username") == null) {
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

async function loadTopic() {
    await fetch("/data/titles")
        .then(res => res.json())
        .then(data => title = data)

    for (let i = 0;i < title.length; i++) {

        let node = document.getElementById("questionList");
        let cnode = document.createElement('div');
        cnode.setAttribute('class','feature col');
        let ccnode = document.createElement('h2');
        ccnode.innerText = title[i]['title'];
        cnode.appendChild(ccnode);

        s = "<a href=\"#\" class=\"btn btn-primary\" onclick=\"window.location.href='htndosagelist.html'\">\n" +
            "                    Fill in the blank\n" +
            "                </a>\n" +
            "                <a href=\"#\" class=\"btn btn-primary\" onclick=\"window.location.href='htndosagelist_multiple.html'\">\n" +
            "                    Multiple Choice\n" +
            "                </a>";

        cnode.insertAdjacentHTML("beforeend",s);
        node.appendChild(cnode);
    }
    getUsername();
}

function logout() {
    localStorage.setItem("username", "");
    window.location.href = 'index.html';
}

