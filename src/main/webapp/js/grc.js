function getDate(offsetDay) {
    var today = new Date();
    var year = today.getFullYear();
    var month = today.getMonth() + 1;
    var date = today.getDate() + offsetDay;
    month = month > 9 ? month : '0' + month;
    date = date > 9 ? date : '0' + date;

    return year + '-' + month + '-' + date;
}

function getDatetime(offsetDay) {
    var today = new Date();
    var year = today.getFullYear();
    var month = today.getMonth() + 1;
    var date = today.getDate() + offsetDay;
    month = month > 9 ? month : '0' + month;
    date = date > 9 ? date : '0' + date;

    return year + '-' + month + '-' + date + ' 00:00:00';
}

function getRandomColor() {
    var color = ['blue', 'orange', 'yellow', 'olive', 'green', 'teal', 'violet', 'purple', 'pink', 'brown'];
    var r = Math.floor(parseInt(Math.random() * 10));
    return color[r];
}