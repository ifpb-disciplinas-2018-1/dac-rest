import { Component } from '@angular/core';
import { NavController, NavParams, ToastController } from 'ionic-angular';
import { IntegrantesProvider } from '../../providers/integrantes/integrantes';
import { Integrante } from '../../model/integrante';

@Component({
  selector: 'page-list',
  templateUrl: 'list.html'
})
export class ListPage {

  private integrantes: any;

  constructor(public navCtrl: NavController, public navParams: NavParams,
    private service: IntegrantesProvider, private toast: ToastController) {

      // this.service.todosOsIntegrantes()
      // .then(dados => {
      //   this.integrantes = dados;
      // }).catch(error => {
      //   let tem=this.toast.create( {
      //       message: 'Servidor indisponivel',
      //       duration:3000,
      //       position:'top'
      //     }
      //   );
      //   tem.present();
      // })
  }

  ionViewDidEnter() {
    this.service.todosOsIntegrantes()
          .then(dados => {
            this.integrantes = dados;
          }).catch(error => {
            let tem = this.toast.create( {
                message: 'Servidor indisponivel',
                duration:3000,
                position:'top'
              }
            );
            tem.present();
          })
  }
  irParaIntegrante(id: number) {
    this.navCtrl.push('IntegranteDetailPage', { cod: id });
  }
  novoIntegrante() {
    this.navCtrl.push('IntegranteEditPage');
    
  }
  remover(integrante: Integrante) {
    this.service.removerIntegranteComId(integrante.id)
      .then(dados => {
        let index = this.integrantes.indexOf(integrante);
        this.integrantes.splice(index, 1);
      })
  }

  editarIntegrante(integrante: Integrante) {
    this.navCtrl.push('IntegranteEditPage', { integrante: integrante });
  }
}
