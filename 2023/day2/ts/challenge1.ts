import * as fs from 'fs';

const fileName: string = '../input.txt';

fs.readFile(fileName, 'utf-8', (err: any, data: any) => {
    if (err) {
        console.error(`Errore nella lettura del file ${fileName}: ${err.message}`);
        return;
    }

    console.log(`Contenuto del file ${fileName}:`);
    console.log(data);
});