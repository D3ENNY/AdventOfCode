"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var readFile_1 = require("./readFile");
var fileName = "input.txt";
(0, readFile_1.readFile)(fileName)
    .then(function (data) {
    challenge2(data);
})
    .catch(function (err) {
    console.error("Errore nella lettura del file ".concat(fileName, ": ").concat(err.message));
});
function challenge2(data) {
    var result = 0;
    var list = data.split("\n");
    list.forEach(function (el) {
        var set = el.split(":")[1].split(";");
        var power = 0;
        var obj = { "green": 0, "blue": 0, "red": 0 };
        set.forEach(function (i) {
            var resultArray = [];
            i.split(",").forEach(function (j) {
                var _a = j.trim().split(" "), numero = _a[0], colore = _a[1];
                resultArray.push([parseInt(numero), colore]);
            });
            resultArray.forEach(function (j) {
                if (obj[j[1]] < j[0])
                    obj[j[1]] = j[0];
            });
        });
        power = Object.values(obj).reduce(function (acc, val) { return acc * val; }, 1);
        result += power;
    });
    console.log(result);
}
