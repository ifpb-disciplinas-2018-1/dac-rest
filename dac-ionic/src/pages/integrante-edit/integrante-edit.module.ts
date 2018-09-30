import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { IntegranteEditPage } from './integrante-edit';

@NgModule({
  declarations: [
    IntegranteEditPage,
  ],
  imports: [
    IonicPageModule.forChild(IntegranteEditPage),
  ],
})
export class IntegranteEditPageModule {}
