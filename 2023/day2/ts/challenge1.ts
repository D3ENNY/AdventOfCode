import { readFile } from "./readFile";

const fileName: string = "input.txt";

readFile(fileName)
  .then((data) => {
    challenge1(data)
  })
  .catch((err) => {
    console.error(`Errore nella lettura del file ${fileName}: ${err.message}`);
  });



function challenge1(data: string) {
    let list: string[] = data.split("\n")
    let check: { [key: string]: number } = {
        "red" : 12,
        "green" : 13,
        "blue" : 14
    }
    let result = 0;
    
    list.forEach((el: string) => {
        let indexGame: string | undefined= el.split(":")[0].match(new RegExp("\\d+"))?.[0]
        let set: string[] = el.split(":")[1].split(";")
        let flag = false

        set.forEach((i: string) => {
            let resultArray: [number, string][] = []
            i.split(",").forEach((j: string) => {
                let [numero, colore] = j.trim().split(" ")
                resultArray.push([parseInt(numero), colore])
            })
            resultArray.forEach((j: [number, string]) => {        
                if(check[j[1]] < j[0])
                    flag = true
            })
        });
        if(!flag) result += parseInt(indexGame ?? '0')
    })

    console.log(result);
}