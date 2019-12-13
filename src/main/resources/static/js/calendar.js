var taskApi = Vue.resource("/calendar/tasks");


Vue.component('modal', {
    template: `
    <div class="modal is-active">
      <div class="modal-background"></div>
      <div class="modal-content">
        <div class="box"><slot></slot></div>
      </div>
      <button class="modal-close" @click="$emit('close')"></button>
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
        showModal1: false,
        showModal: false,
        showModal3: false,
        showModal4: false,
        showModal5: false,
        showModal6: false,
        showModal7: false,
        showModal8: false,
        showModal9: false,
        showModal10: false,
        showModal11: false,
        showModal12: false,
        showModal13: false,
        showModal14: false,
        showModal15: false,
        showModal16: false,
        showModal17: false,
        showModal18: false,
        showModal19: false,
        showModal20: false,
        showModal21: false,
        showModal22: false,
        showModal23: false,
        showModal24: false,
        showModal25: false,
        showModal26: false,
        showModal27: false,
        showModal28: false,
        showModal29: false,
        showModal30: false,
        showModal31: false,
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
            // console.log(dateA);
            // console.log(dateB);
            if (moment(this.thisYear + '-' + this.monthForTasks + '-' + n).isSame(date)) {
                return true;
            } else {
                return false;
            }
        },
        buttonForTasks: function(n) {
            return '<button class="button is-success" @click="showModal' + n +  '= true">' +
                'show tasks' +
                '</button>'
        },
        modalForTasks: function(n) {
            return '<modal title="Title of the modal" v-show="showModal' + n + '" @close="showModal' + n + ' = false">'
        }
    },
});

