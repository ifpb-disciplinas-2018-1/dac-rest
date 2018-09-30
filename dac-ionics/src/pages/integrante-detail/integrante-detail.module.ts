import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { IntegranteDetailPage } from './integrante-detail';

@NgModule({
  declarations: [
    IntegranteDetailPage,
  ],
  imports: [
    IonicPageModule.forChild(IntegranteDetailPage),
  ],
})
export class IntegranteDetailPageModule {}
