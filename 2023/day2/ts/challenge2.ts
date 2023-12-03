import { readFile } from "./readFile";

const fileName: string = "input.txt";

readFile(fileName)
	.then((data) => {
		challenge2(data)
	})
	.catch((err) => {
		console.error(`Errore nella lettura del file ${fileName}: ${err.message}`);
	});

function challenge2(data: string) {
	let result = 0;
	let list: string[] = data.split("\n")

	list.forEach((el: string) => {
		let set: string[] = el.split(":")[1].split(";")
		let power: number = 0
		let obj:{ [key: string]: number } = {"green": 0, "blue": 0, "red": 0}

		set.forEach((i: string) => {
			let resultArray: [number, string][] = []
			i.split(",").forEach((j: string) => {
				let [numero, colore] = j.trim().split(" ")
				resultArray.push([parseInt(numero), colore])
			})
            resultArray.forEach((j: [number, string]) => {        
				if(obj[j[1]] < j[0])
					obj[j[1]] = j[0]
            })
      	})
		power = Object.values(obj).reduce((acc, val) => acc * val, 1)
		result += power
	})
	console.log(result);
	
}