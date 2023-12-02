"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var fs = require("fs");
var fileName = 'path/al/tuo/file.txt';
fs.readFile(fileName, 'utf-8', function (err, data) {
    if (err) {
        console.error("Errore nella lettura del file ".concat(fileName, ": ").concat(err.message));
        return;
    }
    console.log("Contenuto del file ".concat(fileName, ":"));
    console.log(data);
});
