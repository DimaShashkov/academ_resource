var app2 = new Vue({
    el: '#addTaskButton',
    data: {
        isAddTask: false,
    },
    methods: {
        setIsAddTask: function () {
            this.isAddTask = true;
        }
    },
});
var app1 = new Vue({
    el: '#newJob',
    data: {
        access: true,
    },
    methods: {
        showTask: function () {
        if(app2.isAddTask === true) {
            return true;
        }
        else {
            return false;
        }
        }
    },
});
