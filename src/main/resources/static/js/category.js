let titles;
let idurl;

function setID(id,type) {

    idurl = JSON.parse(sessionStorage.getItem("titles"));

    sessionStorage.setItem("idurl",JSON.stringify(idurl[parseInt(id)]['id']));
    if (type === 1)
        location.href = "FillintheBlank.html";
    else
        location.href = "multipleChoice.html";
}

async function loadTopic() {

    await fetch("/data/titles")
        .then(res => res.json())
        .then(data => titles = data)

    sessionStorage.setItem("titles",JSON.stringify(titles));

    for (let i = 0;i < titles.length; i++) {

        let node = document.getElementById("questionList");
        let cnode = document.createElement('div');
        cnode.setAttribute('class','feature col');
        let ccnode = document.createElement('h2');
        ccnode.innerText = titles[i]['title'];
        cnode.appendChild(ccnode);

        // let divNode = document.createElement('div');
        // divNode.setAttribute('class','feature col');
        // ccnode = document.createElement('a');
        // ccnode.setAttribute('class','btn btn-primary');
        // ccnode.setAttribute('id','f'+i.toString());
        // ccnode.innerText = 'Fill in the blank';
        // ccnode.setAttribute("onclick","setID("+i+")");
        // divNode.append(ccnode);
        // cnode.append(divNode);
        //
        // divNode = document.createElement('div');
        // divNode.setAttribute('class','feature col');
        // ccnode = document.createElement('a');
        // ccnode.setAttribute('class','btn btn-primary');
        // ccnode.setAttribute('id','m'+i.toString());
        // ccnode.innerText = 'Multiple Choice';
        // ccnode.setAttribute("onclick","setID("+i+")");
        // divNode.append(ccnode);
        // cnode.append(divNode);

        ccnode = document.createElement('a');
        ccnode.setAttribute('class','btn btn-primary');
        ccnode.setAttribute('id','f'+i.toString());
        ccnode.innerText = 'Fill in the blank';
        ccnode.setAttribute("onclick","setID("+i+","+1+")");
        cnode.append(ccnode);

        cnode.append(document.createTextNode( '\u00A0' ));

        ccnode = document.createElement('a');
        ccnode.setAttribute('class','btn btn-primary');
        ccnode.setAttribute('id','m'+i.toString());
        ccnode.innerText = 'Multiple Choice';
        ccnode.setAttribute("onclick","setID("+i+","+2+")");
        cnode.append(ccnode);

        node.appendChild(cnode);

    }
}