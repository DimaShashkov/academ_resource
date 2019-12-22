var app2 = new Vue({
    el: '#addTaskButton',
    data: {
        isAddTask: false,
    },
    methods: {
        setIsAddTask: function () {
            this.isAddTask = true;
        },
        setIsAddTaskFalse: function () {
            this.isAddTask = false;
        }
    },
});
var app1 = new Vue({
    el: '#newJob',
    data: {
        access: true,
        showNewJob: false,
    },
    methods: {
        showTask: function () {
        if(app2.isAddTask === true) {
            this.showNewJob = true;
            return true;
        }
        else {
            return false;
        }
        },
        closeNewJob: function () {
            app2.setIsAddTaskFalse();
            this.showNewJob = false;
        }
    },
});
