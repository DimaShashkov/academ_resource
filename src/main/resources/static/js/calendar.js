var taskApi = Vue.resource("/calendar/tasks");


Vue.component('mod', {
    template: `
    <div class="mod is-active">
      <div class="mod-background"></div>
      <div class="mod-cont">
        <div class="box"><slot></slot></div>
      </div>
      <button class="mod-close" @click="$emit('close')"></button>
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
            dateB = moment(date, "MM-DD-YYYY");

            if (moment(this.thisYear + '-' + this.monthForTasks + '-' + n).isSame(date)) {
                if(!this.comtainTasksCount) {
                    // this.comtainTasksCount = true;
                }

                return true;
            } else {

                return false;
            }
        },
        buttonForTasks: function(n) {
            console.log("buttonForTasks");
            this.taskCount = n;
        },
        modalForTasks: function(n) {
            console.log("modalForTasks");
            if(this.taskCount === n) {
                return true;
            }
            else {
                return false;
            }
        },
        closeModalForTasks: function(n) {
            this.taskCount = 0;
            console.log('some doing for close modal window');
        },

        isRenderButton: function(n, tasks) {
            console.log('isRenderButton');
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


