"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.readFile = void 0;
var fs = require("fs");
function readFile(path) {
    return new Promise(function (resolve, reject) {
        fs.readFile(path, 'utf-8', function (err, data) {
            if (err) {
                reject(err);
            }
            else {
                resolve(data);
            }
        });
    });
}
exports.readFile = readFile;
