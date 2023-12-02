"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var readFile_1 = require("./readFile");
var fileName = "input.txt";
(0, readFile_1.readFile)(fileName)
    .then(function (data) {
    challenge1(data);
})
    .catch(function (err) {
    console.error("Errore nella lettura del file ".concat(fileName, ": ").concat(err.message));
});
function challenge1(data) {
    var list = data.split("\n");
    var check = {
        "red": 12,
        "green": 13,
        "blue": 14
    };
    var result = 0;
    list.forEach(function (el) {
        var _a;
        var indexGame = (_a = el.split(":")[0].match(new RegExp("\\d+"))) === null || _a === void 0 ? void 0 : _a[0];
        var set = el.split(":")[1].split(";");
        var flag = false;
        set.forEach(function (i) {
            var resultArray = [];
            i.split(",").forEach(function (j) {
                var _a = j.trim().split(" "), numero = _a[0], colore = _a[1];
                resultArray.push([parseInt(numero), colore]);
            });
            resultArray.forEach(function (j) {
                if (check[j[1]] < j[0])
                    flag = true;
            });
        });
        if (!flag)
            result += parseInt(indexGame !== null && indexGame !== void 0 ? indexGame : '0');
    });
    console.log(result);
}
