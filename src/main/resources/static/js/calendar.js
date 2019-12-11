var taskApi = Vue.resource("/calendar/tasks");


var calendar = new Vue({
    el: '#calendar',
    data: {
        tasks: [],
        title: 'Calendar',
        months: moment().months(),
        today:moment().format("D"),
        thisMonth:moment().format("MMMM"),
        thisYear:moment().format("YYYY"),
        weekDays: moment.weekdays(),
        firstDay: parseInt(moment().startOf('month').format('D')),
        lastDay: parseInt(moment().endOf('month').format('D')),
        firstWeekDay: moment().startOf('month').format("dddd"),
        friday:'Friday',
        thursday:'Thursday',
        counter: 0,
    },
    created: function() {
      taskApi.get().then(result =>
      result.json().then(data => data.forEach( task => this.tasks.push(task)))
      );
        taskApi.get().then(result =>
            result.json().then(data => data.forEach( task => console.log(task)))
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
            this.monthForTasks = moment().add(this.counter, 'months').format('MM');
            // console.log(this.monthForTasks);
            // console.log(this.thisYear);
            // console.log(n);
            // console.log(date);

            dateA = moment(this.monthForTasks + '-' + n + '-' + this.thisYear, "MM-DD-YYYY");
            dateB = moment(date, "MM-DD-YYYY");
            console.log(dateA);
            console.log(dateB);
            if (moment(this.thisYear + '-' + this.monthForTasks + '-' + n).isSame(date)) {
                return true;
            } else {
                return false;
            }
        }
    },
});

