import { Http} from '@angular/http';
import { Injectable } from '@angular/core';
import { Integrante } from '../../model/integrante';

 
@Injectable()
export class IntegrantesProvider {

  private API_URI = "http://localhost:8080/dac-server/api/integrantes/";

  constructor(public http: Http) {
    console.log('Hello IntegrantesProvider Provider');
  }

  todosOsIntegrantes(){
    return new Promise((resolve, reject)=>{
      this.http.get(this.API_URI)
        .subscribe( (result:any)=>{
              resolve(result.json());
          }, (error:any)=>{
              reject(error);
          }
        )
    });
  }
  integranteComId(id:number){
    return new Promise(resolve=>{
      let uri = this.API_URI+id;
      this.http.get(uri)
        .subscribe( (result:any)=>{
            resolve(result.json());
        })
    });
  }
  salvar(integrante:Integrante){
    return new Promise(resolve=>{
      this.http.post(this.API_URI,integrante)
        .subscribe( (result:any)=>{
            resolve(result.json());
        })
    });
  }
  atualizar(integrante:Integrante){
    return new Promise(resolve=>{
      let uri = this.API_URI+integrante.id;
      this.http.put(uri, integrante)
        .subscribe( (result:any)=>{
            resolve(result.json());
        })
    });
  }
  removerIntegranteComId(id:number){
    return new Promise(resolve=>{
      let uri = this.API_URI+id;
      this.http.delete(uri)
        .subscribe( (result:any)=>{
            resolve(result.json());
        })
    });
  }
}
