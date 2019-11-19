var app1 = new Vue({
    el: '#taskTable',
    data: {
        tasks: [
            {id: 1, date: "12-01-2019",
                subject: "Math", text: "some text", link:"https://drive.google.com/file/d/0B_P1E4HWyAiIODBCOGhrdWpMN1k/view"},
            {id: 2, date: "29-08-2019",
                subject: "History", text: "bring a notebook", link: ""}
        ]
    }
})

var app2 = new Vue({
    el: '#newJob',
    data: {
        access: true
    }
})