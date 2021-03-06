let currPos;
let obj;
let correctIndex = 0;

async function showMultipleChoice(questionPos) {

    let url = sessionStorage.getItem('idurl').replace(/"/g,"");
    await fetch(`/data/questionSet?id=${url}`)
        .then(res => res.json())
        .then(data => obj = data)

    console.log(obj);
    // await fetch("/data/htn-dosage-list")
    //     .then(res => res.json())
    //     .then(data => obj = data)

    const getButton = document.getElementById("answerStatus");
    if (getButton.innerText === "Hide Answer")
        getButton.innerText = "Show Answer";

    // Make sure the currPos within the range
    if (questionPos <= 0) {
        currPos = 0;
        document.getElementById("prevButton").disabled = true;
    }

    else if (questionPos >= obj.rows-1) {
        currPos = obj.rows - 1;
        document.getElementById("nextButton").disabled = true;
    }

    else {
        currPos = questionPos;
        document.getElementById("prevButton").disabled = false;
        document.getElementById("nextButton").disabled = false;
    }

    // If the database is greater than 3 question, the max choice can be shown
    // for each question is 4, otherwise, this should equal to the database
    // question number.
    let maxQuestionNum = (obj.rows > 3)? 4 : obj.rows;


    const qNum = document.getElementById("questionNumber");
    qNum.innerText = (currPos+1).toString() + "/" + obj.rows.toString();

    // Remove the previous information, be ready to load new question
    const div = document.getElementById("questionList");
    div.innerHTML = '';

    // Start loading new question data
    document.title = obj.title;
    document.getElementById("title").innerHTML = obj.title;

    let node = document.createElement("h4");
    node.innerHTML = obj.questionAsk + ": " + obj.questions[currPos].questionText;
    document.getElementById("questionList").appendChild(node);

    let usedRandom = [];
    correctIndex = getRandomInt(maxQuestionNum);
    usedRandom.push(currPos);
    for (let i=0; i<maxQuestionNum; i++) {
        let choiceString = '';
        // load the correct answer
        if (i === correctIndex) {

            for(p in obj.questions[currPos].answers) {
                if (obj.questions[currPos].answers[p][0] !== "-1") {
                    choiceString += p;
                    choiceString += ':';
                    choiceString += obj.questions[currPos].answers[p];
                    choiceString += "; ";
                }
            }
        }

        // load other incorrect answers
        else {
            let random = getRandomInt(obj.rows);
            while (usedRandom.includes(random)) {
                random = getRandomInt(obj.rows);
            }
            usedRandom.push(random);

            for(p in obj.questions[random].answers) {
                if (obj.questions[random].answers[p][0] !== "-1") {
                    choiceString += p;
                    choiceString += ':';
                    choiceString += obj.questions[random].answers[p];
                    choiceString += "; ";
                }
            }
        }

        // remove the last ';'
        choiceString = choiceString.substring(0,choiceString.length - 2);

        let node = document.createElement("div");
        node.setAttribute('class','form-check');
        div.appendChild(node);

        let node2 = document.createElement("input");
        node2.setAttribute('class','form-check-input');
        node2.setAttribute('type','radio');
        node2.setAttribute('name', 'flexRadioDefault');
        node2.setAttribute('id','r'+i.toString());
        node.appendChild(node2);

        node2 = document.createElement('h5');
        node2.setAttribute('class','p-3 mb-2 bg-light text-dark');
        node2.setAttribute('id','c'+i.toString());
        node2.innerText = choiceString;
        node.appendChild(node2);
    }
    getUsername()
}


function getRandomInt(max) {
    return Math.floor(Math.random() * max);
}


function showAnswer() {
    const getButton = document.getElementById("answerStatus");
    const status = getButton.innerText;
    let correctNode = document.getElementById('c'+ correctIndex.toString());

    if (status === "Show Answer") {
        getButton.innerText = "Hide Answer";
        correctNode.setAttribute('class','p-3 mb-2 bg-success text-white');
    }

    else {
        getButton.innerText = "Show Answer";
        correctNode.setAttribute('class','p-3 mb-2 bg-light text-dark');
    }
}


function checkAnswer() {

    let maxQuestionNum = (obj.rows > 3)? 4 : obj.rows;
    let userChoice = -1;
    for (let i=0; i<maxQuestionNum; i++) {
        if (document.getElementById('r'+i.toString()).checked === true)
            userChoice = i;
    }

    // if not -1, which means there is one choice got selected
    if (userChoice !== -1) {
        if (userChoice !== correctIndex) {
            let userNode = document.getElementById('c'+ userChoice.toString());
            userNode.setAttribute('class','p-3 mb-2 bg-danger text-white');
        }

        let correctNode = document.getElementById('c'+ correctIndex.toString());
        correctNode.setAttribute('class','p-3 mb-2 bg-success text-white');
    }

}

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