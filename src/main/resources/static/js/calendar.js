var taskApi = Vue.resource("/calendar/tasks");


Vue.component('mod', {
    template: `
<div class="cust">
    <div class="cust-title">
<table class="cust-text" id="">
            <tr>
                <td><h3>Задания</h3></td>
                <td class="rightcol">
                    <button class="btn btn-outline-light" @click="$emit('close')" >
                        <span aria-hidden="true">&times;</span>
                    </button>
                </td>
            </tr>        
        </table>
    </div>
    <div class="cust-body">
    
        <slot></slot>
    </div>
</div>

  `
});

var calendar = new Vue({
    el: '#calendar',
    data: {
        tasks: [],
        monthForTasks : moment().format("MM"),
        title: 'Calendar',
        months: moment().months(),
        today:moment().format("D"),
        realMonth:moment().format("MMMM"),
        thisMonth:moment().format("MMMM"),
        thisYear:moment().format("YYYY"),
        weekDays: moment.weekdays(),
        firstDay: parseInt(moment().startOf('month').format('D')),
        lastDay: parseInt(moment().endOf('month').format('D')),
        firstWeekDay: moment().startOf('month').format("dddd"),
        friday:'Friday',
        thursday:'Thursday',
        counter: 0,
        taskCount: 0,
        comtainTasksCount: false,
    },
    created: function() {
            taskApi.get().then(result =>
                result.json().then(data => data.forEach(task => this.tasks.push(task)))
            );
        console.log(this.tasks);
    },
    methods: {
        previousMonth: function() {
            this.counter--
            this.thisMonth = moment().add(this.counter, 'months').format('MMMM');
            this.lastDay =  parseInt(moment().add(this.counter, 'months').endOf('month').format('D'));
            this.thisYear = moment().add(this.counter, 'months').format("YYYY");
            this.firstWeekDay= moment().add(this.counter, 'months').startOf('month').format("dddd");
            console.log(this.thisMonth)
        },
        nextMonth: function() {
            this.counter++
            this.thisMonth = moment().add(this.counter, 'months').format('MMMM');
            this.lastDay =  parseInt(moment().add(this.counter, 'months').endOf('month').format('D'));
            this.thisYear = moment().add(this.counter, 'months').format("YYYY");
            this.firstWeekDay= moment().add(this.counter, 'months').startOf('month').format("dddd");
            console.log(this.thisMonth)
        },
        currentDate: function(n, date) {
            console.log("currentDate");
            this.monthForTasks = moment().add(this.counter, 'months').format('MM');

            dateA = moment(this.monthForTasks + '-' + n + '-' + this.thisYear, "MM-DD-YYYY");
            dateB = moment(date).format( "MM-DD-YYYY");

            if (moment(dateA).isSame(dateB)) {
                if(!this.comtainTasksCount) {
                }

                return true;
            } else {

                return false;
            }
        },
        buttonForTasks: function(n) {
            this.taskCount = n;
        },
        modalForTasks: function(n) {
            if(this.taskCount === n) {
                return true;
            }
            else {
                return false;
            }
        },
        closeModalForTasks: function(n) {
            this.taskCount = 0;
        },

        isRenderButton: function(n, tasks) {
            var isRender = false;
            for(let i = 0; i < tasks.length; i++) {
                if(this.currentDate(n, tasks[i].date)) {
                    isRender = true;
                }
            }
            return isRender;
        },
    }
});


