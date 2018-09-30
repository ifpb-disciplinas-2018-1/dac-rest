# Template dos arquivos

*Promise*
```
return new Promise( resolve =>{
      this.http.get(this.API_URL).
        subscribe( result =>{
            this.data = result.json();
            resolve(this.data);
        })

    });
```    

 *home.html*
```<ion-header>
  <ion-navbar>
    <button ion-button menuToggle>
      <ion-icon name="menu"></ion-icon>
    </button>
    <ion-title>Home</ion-title>
  </ion-navbar>
</ion-header>

<ion-content padding>
  <h3>DAC</h3>

  <p>
    O objetivo deste aplicativo é consumir a <a href="http://localhost:8080/dac-server/api/integrantes">API</a>
  </p>
</ion-content>```

*list.html*
```<ion-header>
  <ion-navbar>
    <button ion-button menuToggle>
      <ion-icon name="menu"></ion-icon>
    </button>
    <ion-title>Integrantes</ion-title>
  </ion-navbar>
</ion-header>
<ion-content>
  <ion-list>
      <ion-item-sliding *ngFor="let integrante of integrantes">
        <ion-item class="integrante" (click)="irParaIntegrante(integrante.id)">
          <ion-avatar item-left >  
            <img src="{{integrante.foto}}">
          </ion-avatar> 
          <h2>{{integrante.nome}}</h2>
        </ion-item>
        <ion-item-options side="left">
          <button ion-button color="primary" (click)="editarIntegrante(integrante)">
            <ion-icon name="create"></ion-icon>
            editar
          </button> 
        </ion-item-options> 
        <ion-item-options side="right"> 
          <button ion-button color="danger" (click)="remover(integrante)">
              <ion-icon name="trash"></ion-icon>
              excluir
            </button>
        </ion-item-options> 
      </ion-item-sliding>
    </ion-list>
    <ion-fab bottom right>
        <button ion-fab color="secondary" (click)="novoIntegrante()"><ion-icon name="add"></ion-icon></button>
    </ion-fab>
</ion-content>
```

*integrante-detail.html*
```<ion-header>
  <ion-navbar>
    <ion-title>Integrante</ion-title>
  </ion-navbar>
</ion-header>

<ion-content>
    <ion-card>
      <img src="{{integrante.foto}}"/>
      <ion-card-content>
        <ion-card-title>
            {{integrante.nome}}
        </ion-card-title>
        <p>
          Nome:  {{integrante.nome}}
        </p>
        <p>
          Código: {{integrante.id}}
        </p>
        <p>
          Sexo: {{integrante.sexo}}
        </p>
      </ion-card-content>
    </ion-card>
  </ion-content>```


*integrante-edit.html*
```<ion-header>
  <ion-navbar>
    <ion-title>
      Cadastro
    </ion-title>
  </ion-navbar>
</ion-header>
<ion-content padding>
  <ion-list>
    <ion-item>
      <ion-label stacked>Nome</ion-label>
      <ion-input type="text" name="nome" [(ngModel)]="integrante.nome"></ion-input>
    </ion-item>
    <ion-item>
        <ion-label stacked>Sexo</ion-label>
          <ion-select [(ngModel)]="integrante.sexo" >
            <ion-option value="women">Feminino</ion-option>
            <ion-option value="men">Masculino</ion-option>
          </ion-select>
    </ion-item>
  </ion-list>
  <button ion-button block (click)="salvar()" color="primary">Salvar</button>
  <button ion-button block (click)="cancelar()" color="primary">Cancelar</button>
</ion-content>```



