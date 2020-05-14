package com.example.pint_android_v3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class basedados extends SQLiteOpenHelper {
    private Context co;
    /*Variaveis para nome e versao da base de dados*/
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "adm.db";
    /*---------------------------------------------*/
    private String tabsc= "";
    SQLiteDatabase dbw = this.getWritableDatabase();
    SQLiteDatabase dbr = this.getReadableDatabase();

    public basedados(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        co = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private String criar_Utilizador(){
        return "create table UTILIZADOR (" +
                "   ID_UTILIZADOR        int                  not null," +
                "   NOME_UTILIZADOR      text                 null," +
                "   EMAIL_UTILIZADOR     text                 null," +
                "   PASSWORD_UTILIZADOR  text                 null," +
                "   MORADA_UTILIZADOR    text                 null," +
                "   TELEFONE_UTILIZADOR  int                  null," +
                "   CC_UTILIZADOR        int                  null," +
                "   NIF_UTILIZADOR       int                  null," +
                "   constraint PK_UTILIZADOR primary key (ID_UTILIZADOR)" +
                ")";
    }
    private String criar_Operador_Transporte(){
        return "create table OPERADOR_TRANSPORTE (" +
                "   ID_UTILIZADOR        int                  not null," +
                "   ID_OPERADOR          int                  not null," +
                "   constraint PK_OPERADOR_TRANSPORTE primary key (ID_UTILIZADOR, ID_OPERADOR)," +
                "   constraint FK_OPERADOR_TIPO_UTIL_UTILIZAD foreign key (ID_UTILIZADOR)" +
                "      references UTILIZADOR (ID_UTILIZADOR)" +
                ")";
    }
    private String criar_Admistrador(){
        return "create table ADMINISTRADOR (" +
                "   ID_UTILIZADOR        int                  not null," +
                "   ID_OPERADOR          int                  not null," +
                "   N_ADMINISTRADOR      int                  not null," +
                "   constraint PK_ADMINISTRADOR primary key (ID_UTILIZADOR, ID_OPERADOR)," +
                "   constraint FK_ADMINISTRADOR_TIPO_OPER_OPERADOR foreign key (ID_UTILIZADOR, ID_OPERADOR)" +
                "      references OPERADOR_TRANSPORTE (ID_UTILIZADOR, ID_OPERADOR)" +
                ")";
    }
    private String criar_Admistrativo(){
        return "create table ADMINISTRATIVO (" +
                "   ID_UTILIZADOR        int                  not null," +
                "   ID_OPERADOR          int                  not null," +
                "   N_ADMINISTRATIVO     int                  not null," +
                "   constraint PK_ADMINISTRATIVO primary key (ID_UTILIZADOR, ID_OPERADOR)," +
                "   constraint FK_ADMINISTRATIVO_TIPO_OPER_OPERADOR foreign key (ID_UTILIZADOR, ID_OPERADOR)" +
                "      references OPERADOR_TRANSPORTE (ID_UTILIZADOR, ID_OPERADOR)" +
                ")";
    }
    private String criar_Tipo_Pedido(){
        return "create table TIPO_PEDIDO (" +
                "   ID_TIPO_PEDIDO       int                  not null," +
                "   TIPO_PEDIDO          text                 null," +
                "   constraint PK_TIPO_PEDIDO primary key (ID_TIPO_PEDIDO)" +
                ")";
    }
    private String criar_Localidade(){
        return "create table LOCALIDADE (" +
                "   ID_LOCALIDADE        int                  not null," +
                "   NOME_LOCALIDADE      text                 null," +
                "   CODIGO_AREA_LOCALIDADE int                  null," +
                "   PONTOS_PARAGEM       text                 null," +
                "   constraint PK_LOCALIDADE primary key (ID_LOCALIDADE)" +
                ")";
    }
    private String criar_Cidadao(){
        return "create table CIDADAO (" +
                "   ID_UTILIZADOR        int                  not null," +
                "   ID_LOCALIDADE        int                  not null," +
                "   N_CIDADAO            int                  null," +
                "   constraint PK_CIDADAO primary key (ID_UTILIZADOR)," +
                "   constraint FK_CIDADAO_PERTENCE_LOCALIDA foreign key (ID_LOCALIDADE)" +
                "      references LOCALIDADE (ID_LOCALIDADE)," +
                "   constraint FK_CIDADAO_TIPO_UTIL_UTILIZAD foreign key (ID_UTILIZADOR)" +
                "      references UTILIZADOR (ID_UTILIZADOR)" +
                ")";
    }
    private String criar_Veiculo(){
        return "create table VEICULO (" +
                "   ID_VEICULO           int                  not null," +
                "   PROPRIETARIO_VEICULO text                 null," +
                "   MATRICULA_VEICULO    int                  null," +
                "   ANO_VEICULO          datetime             null," +
                "   N_APOLICE_VEICULO    int                  null," +
                "   DATA_FIM_APOLICE_VEICULO datetime             null," +
                "   CATEGORIA_VEICULO    text                 null," +
                "   TIPO_VEICULO         text                 null," +
                "   CAPACIDADE_VEICULO   int                  null," +
                "   constraint PK_VEICULO primary key (ID_VEICULO)" +
                ")";
    }
    private String criar_Motorista(){
        return "create table MOTORISTA (" +
                "   ID_UTILIZADOR        int                  not null," +
                "   ID_OPERADOR          int                  not null," +
                "   N_MOTORISTA          int                  not null," +
                "   TIPO_CARTA_CONDUCAO  text                 not null," +
                "   OBS_MOTORISTA_VIAGEM text                 null," +
                "   PAGOU_VIAGEM         bit                  not null," +
                "   COMPARENCIA_VIAGEM   bit                  not null," +
                "   constraint PK_MOTORISTA primary key (ID_UTILIZADOR, ID_OPERADOR)," +
                "   constraint FK_MOTORIST_TIPO_OPER_OPERADOR foreign key (ID_UTILIZADOR, ID_OPERADOR)" +
                "      references OPERADOR_TRANSPORTE (ID_UTILIZADOR, ID_OPERADOR)" +
                ")";
    }
    private String criar_Viagem(){
        return "create table VIAGEM (" +
                "   ID_VIAGEM            int                  not null," +
                "   ID_VEICULO           int                  not null," +
                "   MOTORISTA_ID_UTILIZADOR int                  not null," +
                "   MOTORISTA_ID_OPERADOR int                  not null," +
                "   ADMINISTRADOR_ID_UTILIZADOR int                  not null," +
                "   ADMINISTRADOR_ID_OPERADOR int                  not null," +
                "   TIPO_VIAGEM          text                 null," +
                "   CLASSIFICACAO_VIAGEM int                  null," +
                "   constraint PK_VIAGEM primary key (ID_VIAGEM)," +
                "   constraint FK_VIAGEM_CRIA_ADMINIST foreign key (ADMINISTRADOR_ID_UTILIZADOR, ADMINISTRADOR_ID_OPERADOR)" +
                "      references ADMINISTRADOR (ID_UTILIZADOR, ID_OPERADOR)," +
                "   constraint FK_VIAGEM_AFILIADO_VEICULO foreign key (ID_VEICULO)" +
                "      references VEICULO (ID_VEICULO)," +
                "   constraint FK_VIAGEM_ALOCADO_MOTORIST foreign key (MOTORISTA_ID_UTILIZADOR, MOTORISTA_ID_OPERADOR)" +
                "      references MOTORISTA (ID_UTILIZADOR, ID_OPERADOR)" +
                ")";
    }
    private String criar_Pedidos(){
        return "create table PEDIDOS (" +
                "   ID_PEDIDO            int                  not null," +
                "   ID_TIPO_PEDIDO       int                  not null," +
                "   ID_VIAGEM            int                  not null," +
                "   CIDADAO_ID_UTILIZADOR int                  not null," +
                "   ADMINISTRADOR_ID_UTILIZADOR int                  null," +
                "   ADMINISTRADOR_ID_OPERADOR int                  null," +
                "   BAGAGEM_PEDIDO       bit                  null," +
                "   NECESSIDADESESPECIAIS_PEDIDO bit                  null," +
                "   CANCELAR_PEDIDO      bit                  null," +
                "   DATA_HORAS_CANCELAMENTO datetime             null," +
                "   LOCAL_ORIGEM_PEDIDO  text                 null," +
                "   LOCAL_DESTINO_PEDIDO text                 null," +
                "   HORAS_PEDIDO         int                  null," +
                "   DATA_PEDIDO          datetime             null," +
                "   DIA_VIAGEM           datetime             null," +
                "   HORA_RECOLHA_VIAGEM  int                  null," +
                "   constraint PK_PEDIDOS primary key (ID_PEDIDO)," +
                "   constraint FK_PEDIDOS_TEM_UM_TIPO_PED foreign key (ID_TIPO_PEDIDO)" +
                "      references TIPO_PEDIDO (ID_TIPO_PEDIDO)," +
                "   constraint FK_PEDIDOS_FAZ_CIDADAO foreign key (CIDADAO_ID_UTILIZADOR)" +
                "      references CIDADAO (ID_UTILIZADOR)," +
                "   constraint FK_PEDIDOS_ASSOCIADO_VIAGEM foreign key (ID_VIAGEM)" +
                "      references VIAGEM (ID_VIAGEM)," +
                "   constraint FK_PEDIDOS_ANALISA_ADMINIST foreign key (ADMINISTRADOR_ID_UTILIZADOR, ADMINISTRADOR_ID_OPERADOR)" +
                "      references ADMINISTRADOR (ID_UTILIZADOR, ID_OPERADOR)" +
                ")";
    }
    private String criar_Bagagem(){
        return "create table BAGAGEM (" +
                "   ID_BAGAGEM           int                  not null," +
                "   ID_PEDIDO            int                  not null," +
                "   NUM_BAGAGENS         int                  null," +
                "   TIPO_BAGAGEM         text                 null," +
                "   constraint PK_BAGAGEM primary key (ID_BAGAGEM)," +
                "   constraint FK_BAGAGEM_PODE_TER_PEDIDOS foreign key (ID_PEDIDO)" +
                "      references PEDIDOS (ID_PEDIDO)" +
                ")";
    }
    private String criar_Banir(){
        return "create table BANIR (" +
                "   CIDADAO_ID_UTILIZADOR int                  not null," +
                "   ADMINISTRADOR_ID_UTILIZADOR int                  not null," +
                "   ADMINISTRADOR_ID_OPERADOR int                  not null," +
                "   constraint PK_BANIR primary key (CIDADAO_ID_UTILIZADOR, ADMINISTRADOR_ID_UTILIZADOR, ADMINISTRADOR_ID_OPERADOR)," +
                "   constraint FK_BANIR_BANIR_CIDADAO foreign key (CIDADAO_ID_UTILIZADOR)" +
                "      references CIDADAO (ID_UTILIZADOR)," +
                "   constraint FK_BANIR_BANIR2_ADMINIST foreign key (ADMINISTRADOR_ID_UTILIZADOR, ADMINISTRADOR_ID_OPERADOR)" +
                "      references ADMINISTRADOR (ID_UTILIZADOR, ID_OPERADOR)" +
                ")";
    }
    private String criar_Cidado_Classifica_Motorista(){
        return "create table CIDADAO_CLASSIFICA_MOTORISTA (" +
                "   ID_CLASSF            int                  not null," +
                "   CIDADAO_ID_UTILIZADOR int                  not null," +
                "   MOTORISTA_ID_UTILIZADOR int                  not null," +
                "   MOTORISTA_ID_OPERADOR int                  not null," +
                "   CLASSIFICACAO        int                  null," +
                "   constraint PK_CIDADAO_CLASSIFICA_MOTORIST primary key (ID_CLASSF)," +
                "   constraint FK_CIDADAO__CLASSIFIC_CIDADAO foreign key (CIDADAO_ID_UTILIZADOR)" +
                "      references CIDADAO (ID_UTILIZADOR)," +
                "   constraint FK_CIDADAO__CIDADAO_C_MOTORIST foreign key (MOTORISTA_ID_UTILIZADOR, MOTORISTA_ID_OPERADOR)" +
                "      references MOTORISTA (ID_UTILIZADOR, ID_OPERADOR)" +
                ")";
    }
    private String criar_CM(){
        return "create table CM (" +
                "   CM_ID_UTILIZADOR     int                  not null," +
                "   N_CM                 int                  not null," +
                "   NOME_CM              text                 not null," +
                "   MORADA_CM            text                 not null," +
                "   constraint PK_CM primary key (CM_ID_UTILIZADOR)," +
                "   constraint FK_CM_TIPO_UTIL_UTILIZAD foreign key (CM_ID_UTILIZADOR)" +
                "      references UTILIZADOR (ID_UTILIZADOR)" +
                ")";
    }
    private String criar_Coimas(){
        return "create table COIMAS (" +
                "   ID_COIMA             int                  not null," +
                "   CIDADAO_ID_UTILIZADOR int                  not null," +
                "   PASSAGEIRO_COIMA     text                 null," +
                "   ESTADO_COIMA         bit                  null," +
                "   constraint PK_COIMAS primary key (ID_COIMA)," +
                "   constraint FK_COIMAS_POSSUI_CIDADAO foreign key (CIDADAO_ID_UTILIZADOR)" +
                "      references CIDADAO (ID_UTILIZADOR)" +
                ")";
    }
    private String criar_Efetua(){
        return "create table EFETUA (" +
                "   CIDADAO_ID_UTILIZADOR int                  not null," +
                "   ID_VIAGEM            int                  not null," +
                "   constraint PK_EFETUA primary key (CIDADAO_ID_UTILIZADOR, ID_VIAGEM)," +
                "   constraint FK_EFETUA_EFETUA_CIDADAO foreign key (CIDADAO_ID_UTILIZADOR)" +
                "      references CIDADAO (ID_UTILIZADOR)," +
                "   constraint FK_EFETUA_EFETUA2_VIAGEM foreign key (ID_VIAGEM)" +
                "      references VIAGEM (ID_VIAGEM)" +
                ")";
    }
    private String criar_Modalidade(){
        return "create table MODALIDADE (" +
                "   ID_MODALIDADE        int                  not null," +
                "   ID_PEDIDO            int                  not null," +
                "   TIPO_MODALIDADE      bit                  null," +
                "   constraint PK_MODALIDADE primary key (ID_MODALIDADE)," +
                "   constraint FK_MODALIDA_MODALIDAD_PEDIDOS foreign key (ID_PEDIDO)" +
                "      references PEDIDOS (ID_PEDIDO)" +
                ")";
    }
    private String criar_Necessidades_Especiais(){
        return "create table NECESSIDADES_ESPECIAIS (" +
                "   ID_NECESSIDADE       int                  not null," +
                "   ID_PEDIDO            int                  not null," +
                "   NOME_NECESSIDADE     text                 null," +
                "   constraint PK_NECESSIDADES_ESPECIAIS primary key (ID_NECESSIDADE)," +
                "   constraint FK_NECESSID_NECESSI_P_PEDIDOS foreign key (ID_PEDIDO)" +
                "      references PEDIDOS (ID_PEDIDO)" +
                ")";
    }
    private String criar_Pagamento(){
        return "create table PAGAMENTO (" +
                "   ID_PAGAMENTO         int                  not null," +
                "   ID_VIAGEM            int                  not null," +
                "   CIDADAO_ID_UTILIZADOR int                  not null," +
                "   MOTORISTA_ID_UTILIZADOR int                  not null," +
                "   MOTORISTA_ID_OPERADOR int                  not null," +
                "   ADMINISTRATIVO_ID_UTILIZADOR int                  not null," +
                "   ADMINISTRATIVO_ID_OPERADOR int                  not null," +
                "   VALIDACAO_PAGAMENTO  bit                  null," +
                "   DATA_PAGAMENTO       datetime             null," +
                "   constraint PK_PAGAMENTO primary key (ID_PAGAMENTO)," +
                "   constraint FK_PAGAMENT_TEM_VIAGEM foreign key (ID_VIAGEM)" +
                "      references VIAGEM (ID_VIAGEM)," +
                "   constraint FK_PAGAMENT_REALIZA_U_CIDADAO foreign key (CIDADAO_ID_UTILIZADOR)" +
                "      references CIDADAO (ID_UTILIZADOR)," +
                "   constraint FK_PAGAMENT_VERIFICA_MOTORIST foreign key (MOTORISTA_ID_UTILIZADOR, MOTORISTA_ID_OPERADOR)" +
                "      references MOTORISTA (ID_UTILIZADOR, ID_OPERADOR)," +
                "   constraint FK_PAGAMENT_ADMINISTR_ADMINIST foreign key (ADMINISTRATIVO_ID_UTILIZADOR, ADMINISTRATIVO_ID_OPERADOR)" +
                "      references ADMINISTRATIVO (ID_UTILIZADOR, ID_OPERADOR)" +
                ")";
    }
    private String criar_Percurso(){
        return "create table PERCURSO (" +
                "   ID_VIAGEM            int                  not null," +
                "   ID_LOCALIDADE        int                  not null," +
                "   LOCAL_ORIGEM         text                 null," +
                "   LOCAL_DESTINO        text                 null," +
                "   ORDEM                text                 null," +
                "   constraint PK_PERCURSO primary key (ID_VIAGEM, ID_LOCALIDADE)," +
                "   constraint FK_PERCURSO_PERCURSO_VIAGEM foreign key (ID_VIAGEM)" +
                "      references VIAGEM (ID_VIAGEM)," +
                "   constraint FK_PERCURSO_PERCURSO2_LOCALIDA foreign key (ID_LOCALIDADE)" +
                "      references LOCALIDADE (ID_LOCALIDADE)" +
                ")";
    }
    private String criar_Telefonista(){
        return "create table TELEFONISTA (" +
                "   ID_UTILIZADOR        int                  not null," +
                "   ID_OPERADOR          int                  not null," +
                "   N_TELEFONISTA        int                  not null," +
                "   constraint PK_TELEFONISTA primary key (ID_UTILIZADOR, ID_OPERADOR)," +
                "   constraint FK_TELEFONI_TIPO_OPER_OPERADOR foreign key (ID_UTILIZADOR, ID_OPERADOR)" +
                "      references OPERADOR_TRANSPORTE (ID_UTILIZADOR, ID_OPERADOR)" +
                ")";
    }
    private String criar_Telefonista_Pedido(){
        return "create table TELEFONISTA_PEDIDO (" +
                "   ID_PEDIDO            int                  not null," +
                "   TELEFONISTA_ID_UTILIZADOR int                  not null," +
                "   TELEFONISTA_ID_OPERADOR int                  not null," +
                "   DATA_PEDIDO_TELEFONISTA datetime             not null," +
                "   constraint PK_TELEFONISTA_PEDIDO primary key (ID_PEDIDO)," +
                "   constraint FK_TELEFONI_PEDIDO_TE_PEDIDOS foreign key (ID_PEDIDO)" +
                "      references PEDIDOS (ID_PEDIDO)," +
                "   constraint FK_TELEFONI_TELEF_PED_TELEFONI foreign key (TELEFONISTA_ID_UTILIZADOR, TELEFONISTA_ID_OPERADOR)" +
                "      references TELEFONISTA (ID_UTILIZADOR, ID_OPERADOR)" +
                ")";
    }
    private String criar_Ternaria(){
        return "create table TERNARIA (" +
                "   ID_VIAGEM            int                  not null," +
                "   ID_LOCALIDADE        int                  not null," +
                "   CIDADAO_ID_UTILIZADOR int                  not null," +
                "   NTERNARIA            int                  null," +
                "   constraint PK_TERNARIA primary key (ID_VIAGEM)," +
                "   constraint FK_TERNARIA_TERNARIA__CIDADAO foreign key (CIDADAO_ID_UTILIZADOR)" +
                "      references CIDADAO (ID_UTILIZADOR)," +
                "   constraint FK_TERNARIA_TERNARIA__VIAGEM foreign key (ID_VIAGEM)" +
                "      references VIAGEM (ID_VIAGEM)," +
                "   constraint FK_TERNARIA_TERNARIA__LOCALIDA foreign key (ID_LOCALIDADE)" +
                "      references LOCALIDADE (ID_LOCALIDADE)" +
                ")";
    }
    public void criar_tabelas(SQLiteDatabase db){
        try {
            db.execSQL(criar_Utilizador());
            db.execSQL(criar_Operador_Transporte());
            db.execSQL(criar_Admistrador());
            db.execSQL(criar_Admistrativo());
            db.execSQL(criar_Tipo_Pedido());
            db.execSQL(criar_Localidade());
            db.execSQL(criar_Cidadao());
            db.execSQL(criar_Veiculo());
            db.execSQL(criar_Motorista());
            db.execSQL(criar_Viagem());
            db.execSQL(criar_Pedidos());
            db.execSQL(criar_Bagagem());
            db.execSQL(criar_Banir());
            db.execSQL(criar_Cidado_Classifica_Motorista());
            db.execSQL(criar_CM());
            db.execSQL(criar_Coimas());
            db.execSQL(criar_Efetua());
            db.execSQL(criar_Modalidade());
            db.execSQL(criar_Necessidades_Especiais());
            db.execSQL(criar_Pagamento());
            db.execSQL(criar_Percurso());
            db.execSQL(criar_Telefonista());
            db.execSQL(criar_Telefonista_Pedido());
            db.execSQL(criar_Ternaria());
            Log.i("Tabelas","As tabelas foram criadas");
        }
        catch (Exception e){
            Log.i("Erro", "Erro ao criar tabela//Erro: " + e);
        }
    }//Cria as Tabelas da Base Dados

    public void apagar_Tabelas(SQLiteDatabase db){
        try {
            tabsc = "DROP TABLE IF EXISTS TERNARIA";
            db.execSQL(tabsc);
            tabsc = "DROP TABLE IF EXISTS TELEFONISTA_PEDIDO";
            db.execSQL(tabsc);
            tabsc = "DROP TABLE IF EXISTS TELEFONISTA";
            db.execSQL(tabsc);
            tabsc = "DROP TABLE IF EXISTS PERCURSO";
            db.execSQL(tabsc);
            tabsc = "DROP TABLE IF EXISTS PAGAMENTO";
            db.execSQL(tabsc);
            tabsc = "DROP TABLE IF EXISTS NECESSIDADES_ESPECIAIS";
            db.execSQL(tabsc);
            tabsc = "DROP TABLE IF EXISTS MODALIDADE";
            db.execSQL(tabsc);
            tabsc = "DROP TABLE IF EXISTS EFETUA";
            db.execSQL(tabsc);
            tabsc = "DROP TABLE IF EXISTS COIMAS";
            db.execSQL(tabsc);
            tabsc = "DROP TABLE IF EXISTS CM";
            db.execSQL(tabsc);
            tabsc = "DROP TABLE IF EXISTS CIDADAO_CLASSIFICA_MOTORISTA" ;
            db.execSQL(tabsc);
            tabsc = "DROP TABLE IF EXISTS BANIR";
            db.execSQL(tabsc);
            tabsc = "DROP TABLE IF EXISTS BAGAGEM";
            db.execSQL(tabsc);
            tabsc = "DROP TABLE IF EXISTS PEDIDOS";
            db.execSQL(tabsc);
            tabsc = "DROP TABLE IF EXISTS VIAGEM";
            db.execSQL(tabsc);
            tabsc = "DROP TABLE IF EXISTS MOTORISTA";
            db.execSQL(tabsc);
            tabsc = "DROP TABLE IF EXISTS VEICULO";
            db.execSQL(tabsc);
            tabsc = "DROP TABLE IF EXISTS CIDADAO";
            db.execSQL(tabsc);
            tabsc = "DROP TABLE IF EXISTS LOCALIDADE";
            db.execSQL(tabsc);
            tabsc = "DROP TABLE IF EXISTS TIPO_PEDIDO";
            db.execSQL(tabsc);
            tabsc = "DROP TABLE IF EXISTS ADMINISTRATIVO";
            db.execSQL(tabsc);
            tabsc = "DROP TABLE IF EXISTS ADMINISTRADOR";
            db.execSQL(tabsc);
            tabsc = "DROP TABLE IF EXISTS OPERADOR_TRANSPORTE";
            db.execSQL(tabsc);
            tabsc = "DROP TABLE IF EXISTS UTILIZADOR";
            db.execSQL(tabsc);;
            Log.i("Tabelas","As tabelas foram apagadas");
        }
        catch (Exception e){
            Log.i("Erro", "Erro ao apagar tabela//Erro: " + e);
        }
    }//Apaga as Tabelas da Base Dados

    public void InserirUtilizadoresParaTeste(SQLiteDatabase db){
        tabsc = "insert into UTILIZADOR values('1','Joao Estima','jE@estv.ipv.pt','123456','null','1','1','1')" ;
        db.execSQL(tabsc);
        tabsc = "insert into UTILIZADOR values('2','Joao Pedro','joaojoao@estv.ipv.pt','123456','null', '1','1','1')" ;
        db.execSQL(tabsc);
        tabsc = "insert into UTILIZADOR values('3','Reun','reun@gmail.com','bola','null', '1','1','1')" ;
        db.execSQL(tabsc);
        Log.i("Done", "Valores inseridos");
    }

    public boolean confirmarLogin(SQLiteDatabase db, String Email, String Password){
        boolean Existe = false;
        Log.i("Entrada", "Entrei no confirmarLogin");
        Log.i("Dados", "Mail: [" + Email + "] Pass: [" + Password + "]");
        if(Email.isEmpty() || Password.isEmpty()){
            Log.i("Erro...", "Password ou email nao preenchidos");
            return false;
        }

        Cursor utilizadores = db.rawQuery("select EMAIL_UTILIZADOR, PASSWORD_UTILIZADOR From UTILIZADOR", null);
        utilizadores.moveToFirst();

        try {
            while (utilizadores.isAfterLast() == false) {
                Log.i("Utilizador", "Mail: [" + utilizadores.getString(utilizadores.getColumnIndex("EMAIL_UTILIZADOR"))
                        + "] Pass: [" + utilizadores.getString(utilizadores.getColumnIndex("PASSWORD_UTILIZADOR")) + "]");
                if (Email.equalsIgnoreCase(utilizadores.getString(utilizadores.getColumnIndex("EMAIL_UTILIZADOR")))) {
                    Log.i("Existe o email:", "O Email foi confirmado, falta a pass");
                    if(Password.equalsIgnoreCase(utilizadores.getString(utilizadores.getColumnIndex("PASSWORD_UTILIZADOR")))){
                        Log.i("Existe a PassWord:", "O Email foi confirmado e a pass");
                        Existe = true;
                    }
                }
                utilizadores.moveToNext();
            }
        }
        catch (Exception e){
            Log.i("Erro", "Erro ao procurar na tabela//Erro: " + e);
        }
        return Existe;
    }

}
