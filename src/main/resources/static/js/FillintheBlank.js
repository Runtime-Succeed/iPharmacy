async function showQuestion(questionPos) {

    let url = sessionStorage.getItem('idurl').replace(/"/g,"");

    // ./json/HTN_Dosage_List (3).json
    // /data/htn-dosage-list
    // ./json/HTN_alternative.json
    await fetch(`/data/questionSet?id=${url}`)
        .then(res => res.json())
        .then(data => obj = data)
    //.then(() => console.log(obj))  // for debug, don't delete


    // Make sure the currPos within the range
    if (questionPos < 0)
        currPos = 0;
    else if (questionPos > obj.rows-1)
        currPos = obj.rows - 1;
    else
        currPos = questionPos;

    const qNum = document.getElementById("questionNumber");
    qNum.innerText = (currPos+1).toString() + "/" + obj.rows.toString();

    const getButton = document.getElementById("answerStatus");
    if (getButton.innerText === "Hide Answer")
        getButton.innerText = "Show Answer";

    // Remove the previous information, be ready to load new question
    const div = document.getElementById("questionList");
    div.innerHTML = '';

    // Start loading new question data
    document.title = obj.title;
    document.getElementById("title").innerHTML = obj.title;

    let node = document.createElement("h4");
    node.innerHTML = obj.questionAsk + ": " + obj.questions[currPos].questionText;
    document.getElementById("questionList").appendChild(node);

    for (let i=0; i<obj.answerCols.length; i++) {

        node = document.createElement("h4");

        const question = document.createElement("text");
        question.setAttribute("id", currPos.toString());
        question.innerHTML = obj.answerCols[i] + ": "
        node.appendChild(question);
        document.getElementById("questionList").appendChild(node);


        // If answer == -1, meaning there is no answer needed
        const q = obj.answerCols[i];
        const a = obj.questions[currPos].answers[q][0];

        let inputSpace = document.createElement("input");
        inputSpace.setAttribute("id", "a" + i.toString());
        inputSpace.setAttribute("class", "form-control");
        node.appendChild(inputSpace);
        document.getElementById("questionList").appendChild(node);

        // disable the input field is no answer needed
        if (a === "-1")
            inputSpace.setAttribute("disabled", "disabled");
    }
    getUsername()
}

function showAnswer() {
    let answer;
    let answerSize;
    let inputSpace;
    let question;
    let idName;
    const getButton = document.getElementById("answerStatus");
    const status = getButton.innerText;

    if (status === "Show Answer") {
        getButton.innerText = "Hide Answer";
        for (let i=0; i<obj.answerCols.length; i++) {
            idName = 'a' + i.toString();
            question = obj.answerCols[i];
            inputSpace = document.getElementById(idName);
            answerSize = obj.questions[currPos].answers[question].length;
            answer = '';
            if (answerSize === 1) {
                answer = obj.questions[currPos].answers[question][0];
            }

            else {
                for (let j=0; j<answerSize; j++) {
                    answer += obj.questions[currPos].answers[question][j];
                    if (j !== answerSize-1)
                        answer += ", ";
                }
            }

            if (answer !== "-1")
                inputSpace.setAttribute("placeholder", answer);
        }
    }

    else {
        getButton.innerText = "Show Answer";
        for (let i=0; i<obj.answerCols.length; i++) {
            idName = 'a' + i.toString();
            question = obj.answerCols[i];
            inputSpace = document.getElementById(idName);
            answerSize = obj.questions[currPos].answers[question].length;
            answer = obj.questions[currPos].answers[question][0];
            if (answer !== "-1")
                inputSpace.setAttribute("placeholder", '');
        }
    }
}

function checkAnswer() {

    for (let i=0; i<obj.answerCols.length; i++) {

        var inputSpace = document.getElementById("a" + i.toString());
        var userAns = inputSpace.value;
        var question = obj.answerCols[i];
        var correctAns = obj.questions[currPos].answers[question];
        let correctAnsCopy;

        // If single answer
        if (correctAns.length === 1) {
            if (correctAns[0] !== "-1") {
                userAns = userAns.trim().toLowerCase();
                correctAnsCopy = correctAns[0].trim().toLowerCase();

                if (userAns === correctAnsCopy)
                    inputSpace.setAttribute("class", "form-control is-valid");
                else
                    inputSpace.setAttribute("class", "form-control is-invalid");
            }
        }

        // If multiple answer
        else {
            let check = false;
            correctAnsCopy = [...correctAns]; // shallow copy
            // User answer should contain comma for multiple answers
            if (userAns.includes(",")) {
                let ansArr = userAns.split(",");
                if (ansArr.length === correctAnsCopy.length) {
                    for (let j=0; j<ansArr.length; j++) {
                        ansArr[j] = ansArr[j].trim().toLowerCase();
                        correctAnsCopy[j] = correctAnsCopy[j].trim().toLowerCase();
                    }

                    // check if two array contain the same element regardless of order
                    check = ansArr.every(function (element) {
                        return correctAnsCopy.includes(element);
                    });
                }
            }

            if (check)
                inputSpace.setAttribute("class", "form-control is-valid");
            else
                inputSpace.setAttribute("class", "form-control is-invalid");
        }
    }
}

function getRandomInt(max) {
    return Math.floor(Math.random() * max);
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