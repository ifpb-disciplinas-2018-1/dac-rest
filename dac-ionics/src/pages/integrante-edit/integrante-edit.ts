import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { Integrante } from '../../model/integrante';
import { IntegrantesProvider } from '../../providers/integrantes/integrantes';

@IonicPage()
@Component({
  selector: 'page-integrante-edit',
  templateUrl: 'integrante-edit.html',
})
export class IntegranteEditPage {

  private integrante:Integrante;
 
  constructor(public navCtrl: NavController, public navParams: NavParams,
    private service:IntegrantesProvider) {
      
      if(navParams.data.integrante){
        this.integrante = navParams.data.integrante;
      }else{
        this.integrante = new Integrante();
      }

  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad IntegranteEditPage');
  }

  salvar(){
      if(this.integrante.id){
        this.service.atualizar(this.integrante)
        .then(dados=>{
          this.navCtrl.pop();
        });
      }else{
        this.service.salvar(this.integrante)
        .then(dados=>{
          this.navCtrl.pop();
        });
    }
  }
  cancelar(){
    this.navCtrl.pop();
  }

}
