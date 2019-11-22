Vue.component('tasksFromBD', {
    props:['tasks'],
    template: '<table cellpadding="4" cellspacing="1" border="1" align=\'center\' >\n' +
        '    <caption>Tasks</caption>\n' +
        '    <thead>\n' +
        '    <tr>\n' +
        '      <th>№</th>\n' +
        '      <th>Data</th>\n' +
        '      <th>Subject</th>\n' +
        '      <th>Text</th>\n' +
        '      <th>Link</th>\n' +
        '      <th>Is done</th>\n' +
        '    </tr>\n' +
        '    </thead>\n' +
        '    <tbody>\n' +
        '    <tr v-for="task in tasks">\n' +
        '      <td>{{ task.id }}</td>\n' +
        '      <td>{{ task.date }}</td>\n' +
        '      <td>{{ task.subject }}</td>\n' +
        '      <td>{{ task.text }}</td>\n' +
        '      <td><a :href="task.link" target="_blank">Link</a></td>\n' +
        '      <td><input type="checkbox" name="isDone">is done</td>\n' +
        '    </tr>\n' +
        '    </tbody>\n' +
        '  </table>'
});

var app1 = new Vue({
    el: '#taskTable',
    template: '<tasksFromBD :tasks="tasks"/>',
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
});

var app2 = new Vue({
    el: '#newJob',
    data: {
        access: true
    }
});