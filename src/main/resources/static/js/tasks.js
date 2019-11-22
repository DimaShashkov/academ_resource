var app1 = new Vue({
    el: '#taskTable',
    data: {
        tasks: [
            {id: 1, date: moment().format("DD-MM-YYYY"),
                subject: "Math", text: "some text", link:"https://drive.google.com/file/d/0B_P1E4HWyAiIODBCOGhrdWpMN1k/view"},
            {id: 2, date: moment().format("DD-MM-YYYY"),
                subject: "History", text: "bring a notebook", link: ""}
        ]
    },
    methods: {
        moment() {
            return moment();
        }
    }
})

var app2 = new Vue({
    el: '#newJob',
    data: {
        access: true
    }
})