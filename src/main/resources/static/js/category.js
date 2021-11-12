let title;

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
}