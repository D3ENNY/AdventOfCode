import * as fs from 'fs';
export function readFile(path: string): Promise<string> {
    return new Promise((resolve, reject) => {
      fs.readFile(path, 'utf-8', (err: any, data: string) => {
        if (err) {
          reject(err);
        } else {
          resolve(data);
        }
      });
    });
  }