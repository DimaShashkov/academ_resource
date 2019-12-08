var calendar = new Vue({
    el: '#calendar',
    data: {
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
    methods: {
        previousMonth: function() {
            this.counter--
            this.thisMonth = moment().add(this.counter, 'months').format('MMMM');
            taskList.thisMonth = this.thisMonth;
            this.lastDay =  parseInt(moment().add(this.counter, 'months').endOf('month').format('D'));
            this.thisYear = moment().add(this.counter, 'months').format("YYYY");
            taskList.thisYear = this.thisYear;
            taskList.year();
            this.firstWeekDay= moment().add(this.counter, 'months').startOf('month').format("dddd");
            console.log(this.thisMonth)
        },
        nextMonth: function() {
            this.counter++
            this.thisMonth = moment().add(this.counter, 'months').format('MMMM');
            taskList.thisMonth = this.thisMonth;
            this.lastDay =  parseInt(moment().add(this.counter, 'months').endOf('month').format('D'));
            this.thisYear = moment().add(this.counter, 'months').format("YYYY");
            taskList.thisYear = this.thisYear;
            taskList.year();
            this.firstWeekDay= moment().add(this.counter, 'months').startOf('month').format("dddd");
            console.log(this.thisMonth)
        }
    },
});

var taskList = new Vue({
    el: '#taskList',
    data: {
        thisMonth: calendar.thisMonth,
        thisYear: calendar.thisYear,
        firstSemesterYear: calendar.thisYear,
        secondSemesterYear: calendar.thisYear
    },
    methods: {
        year: function () {
            if (moment().add(0, 'months').format('MM') > 9) {
                this.firstSemesterYear = moment().add(0, 'months').format("YYYY");
                this.secondSemesterYear = moment().add(1, 'months').format("YYYY");
            } else {
                this.firstSemesterYear = moment().add(-1, 'months').format("YYYY");
                this.secondSemesterYear = moment().add(0, 'months').format("YYYY");
            }
        }
    }

});