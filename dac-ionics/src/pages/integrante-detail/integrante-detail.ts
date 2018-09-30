import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { IntegrantesProvider } from '../../providers/integrantes/integrantes';
import { Integrante } from '../../model/integrante';

/**
 * Generated class for the IntegranteDetailPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-integrante-detail',
  templateUrl: 'integrante-detail.html',
})
export class IntegranteDetailPage {

  private integrante:Integrante;

  constructor(public navCtrl: NavController, public navParams: NavParams,
    private service:IntegrantesProvider) {
    //preencher o objeto integrante
      this.integrante = new Integrante();
      this.service.integranteComId(navParams.data.cod)
        .then(dados=>{
          // this.integrante = dados;
          this.integrante.foto = dados['foto'];
          this.integrante.id = dados['id'];
          this.integrante.nome = dados['nome'];
          this.integrante.sexo = dados['sexo'];
        }
      )
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad IntegranteDetailPage');
  }

}
