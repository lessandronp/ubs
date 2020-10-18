# Preparação do ambiente:

## Banco de dados 

1. Após a realização da instalação do banco de dados Postgres, é necessário criar o database "ubs".
2. Depois que o database for criado, no startup da aplicação existe um método que realiza a criação das tabelas do sistema,
	efetua a leitura do arquivo csv, a normalização dos dados, e preenchimento dos objetos correspondentes.
	O método "instantiateDatabase()" na classe DBService é responsável pela realização dessa ação.
3. Para que o método "instantiateDatabase()" seja disparado, a propriedade "spring.jpa.hibernate.ddl-auto" no application.properties precisa
	estar definida como "create" ou "create-drop".
4. Após a finalização do carregamento, com as tabelas devidamente criadas e populadas, vamos executar o script chamado data.sql, presente
	na pasta src/main/resources na estrutura do projeto. O script realizará os inserts iniciais de ROLE e usuários do sistema.
5. Realize o stop do servidor de aplicação e altere a propriedade "spring.jpa.hibernate.ddl-auto" para "update" ou "none" no application.properties.

## Importação do projeto

1. O projeto foi criado no eclipse, como "Mavem Project". Realize a importação como projeto eclipse, pelo import "Existing project into Workspace".
2. Adicione o tomcat 8.5 como servidor de aplicação.
3. Para realização do deploy coloque o projeto dentro do servidor de aplicação. Se não for possível por algum problema de configuração do projeto,
	altere o arquivo "org.eclipse.wst.common.project.facet.core.xml", na pasta .settings, trocando a tag da versão 4.0 pra 3.1.
* OBS Esse procedimento não é obrigatório, o startup também pode ser efetuado chamando diretamente a classe startup (SpringStartApplication) do Spring Boot.
	
	<installed facet="jst.web" version="4.0"/> por
	<installed facet="jst.web" version="3.1"/> 
	
4. O projeto está utilizando o Lombok. A versão que consegui compatibilidade com o eclipse mais novo (2020-09) foi a lombok-1.16.18.jar.

## Classes de teste

--1. Foram adicionadas classes de teste unitário utilizando o jupiter. Na classe de testes de Post, é possível adicionar um Post com comentário, link ou imagem.
	Também temos a classe de testes de comentário, para possíveis comentários relacionados à um determinado Post, assim a classe de testes de álbum, onde
	é possível realizar a criação de um álbum com imagem, e a classe de testes de usuário. 
--2. Os testes são executados com a versão mais nova, Junit 5. 
--3. A versão que apresentou melhor compatibilidade com o Junit 5 foi a 2020-09.

## EndPoints

### Segurança

1. Para execução da maioria dos endpoints, é obrigatório que o token seja informado no cabeçalho das requisições. A requisição de listagem das
	Ubs não precisam dessa informação. Apenas a requisição que retorna os pontos mais próximos à partir da latitude e longitude informadas.

### Usuários

1. Endpoint de geração do token JWT <br/>
	Requisição post realizada através do endpoint: <span style="font-family: 'Lucida Console'; font-size: 13px">localhost:8080/UBS/api/v1/users/authenticate</span> <br/>
	<span style="font-family: 'Lucida Console'; font-size: 13px; font-weight: bold">
	* OBS: Caso apresente problema na geração do token após a tentativa de autenticação, através da chamada do endpoint citado, favor, alterar a senha
		criprografada na tabela de user_ubs de todos os usuários, pela gerada na classe utilitária GeneratePassword, no pacote util.
	</span>

Exemplo de json de envio: POST

```json
{
    "username" : "user.teste",
    "password" : "123"
}
```

### UBS

1. Endpoint de listagem de Ubs <br/>
	Requisição através do endpoint: <span style="font-family: 'Lucida Console'; font-size: 13px">localhost:8080/UBS/api/v1/allUbs</span> <br />
	Também é possível passar a página e número de registros <span style="font-family: 'Lucida Console'; font-size: 13px">localhost:8080/UBS/api/v1/allUbs?page=20&per_page=3</span> 

Exemplo de retorno da chamada: GET

```json
{
    "content": [
        {
            "id": 20172,
            "name": "AMBULATORIO GERAL MARILENE GIACOMET DE AGUIAR",
            "codCnes": 2512599,
            "phone": "4733939403",
            "address": {
                "id": 19056,
                "street": "RUA NORBERTO SEARA HEUSI",
                "neighborhood": "ASILO",
                "city": {
                    "id": 606,
                    "code": 420240,
                    "name": "Blumenau"
                }
            },
            "score": {
                "id": 13,
                "structureSize": {
                    "id": 1,
                    "description": "Desempenho acima da média"
                },
                "adaptationSenior": {
                    "id": 3,
                    "description": "Desempenho acima da média"
                },
                "medicalEquipment": {
                    "id": 1,
                    "description": "Desempenho mediano ou  um pouco abaixo da média"
                },
                "medicine": {
                    "id": 1,
                    "description": "Desempenho acima da média"
                }
            },
            "geocode": {
                "id": 19299,
                "latitude": "-26.8926429748527",
                "longitude": "-49.0999603271470"
            }
        },
        {
            "id": 30209,
            "name": "AMBULATORIO GERAL MARIO JORGE VIEIRA FORTALEZA",
            "codCnes": 2512556,
            "phone": "4733238633",
            "address": {
                "id": 28187,
                "street": "RUA PAULAHOELTGEBAUM",
                "neighborhood": "FORTALEZA",
                "city": {
                    "id": 606,
                    "code": 420240,
                    "name": "Blumenau"
                }
            },
            "score": {
                "id": 13,
                "structureSize": {
                    "id": 1,
                    "description": "Desempenho acima da média"
                },
                "adaptationSenior": {
                    "id": 3,
                    "description": "Desempenho acima da média"
                },
                "medicalEquipment": {
                    "id": 1,
                    "description": "Desempenho mediano ou  um pouco abaixo da média"
                },
                "medicine": {
                    "id": 1,
                    "description": "Desempenho acima da média"
                }
            },
            "geocode": {
                "id": 28737,
                "latitude": "-26.9190000000000",
                "longitude": "-49.0660000000000"
            }
        },
        {
            "id": 25004,
            "name": "AMBULATORIO GRAVELINA DA COSTA",
            "codCnes": 2283077,
            "phone": "(24)24842799",
            "address": {
                "id": 23454,
                "street": "RUA CATETE",
                "neighborhood": "MIGUEL PEREIRA",
                "city": {
                    "id": 1364,
                    "code": 330290,
                    "name": "Miguel Pereira"
                }
            },
            "score": {
                "id": 21,
                "structureSize": {
                    "id": 1,
                    "description": "Desempenho acima da média"
                },
                "adaptationSenior": {
                    "id": 2,
                    "description": "Desempenho mediano ou  um pouco abaixo da média"
                },
                "medicalEquipment": {
                    "id": 1,
                    "description": "Desempenho mediano ou  um pouco abaixo da média"
                },
                "medicine": {
                    "id": 2,
                    "description": "Desempenho mediano ou  um pouco abaixo da média"
                }
            },
            "geocode": {
                "id": 23861,
                "latitude": "-22.4509048461908",
                "longitude": "-43.3423304557788"
            }
        }
    ],
    "page": 20,
    "size": 3,
    "totalElements": 37685,
    "totalPages": 12562,
    "last": false
}
```

2. Endpoint de listagem de Ubs mais próximas à partir do ponto informado (latitude/longitude) <br/>
	Requisição através do endpoint: <span style="font-family: 'Lucida Console'; font-size: 13px">localhost:8080/UBS/api/v1/find_ubs?query=-23.604936,-46.692999</span> <br />
	Também é possível passar a página e número de registros <span style="font-family: 'Lucida Console'; font-size: 13px">localhost:8080/UBS/api/v1/find_ubs?query=-23.604936,-46.692999&page=0&per_page=3</span> 

Exemplo de retorno da chamada: GET

```json
{
    "content": [
        {
            "id": 2751,
            "name": "UBS REAL PQ PAULO MANGABEIRA ALBERNAZ FILHO",
            "codCnes": 2788470,
            "phone": "1137582329",
            "address": {
                "id": 2697,
                "street": "RUA BARAO MELGACO",
                "neighborhood": "REAL PARQUE",
                "city": {
                    "id": 169,
                    "code": 355030,
                    "name": "São Paulo"
                }
            },
            "score": {
                "id": 3,
                "structureSize": {
                    "id": 3,
                    "description": "Desempenho muito acima da média"
                },
                "adaptationSenior": {
                    "id": 1,
                    "description": "Desempenho muito acima da média"
                },
                "medicalEquipment": {
                    "id": 1,
                    "description": "Desempenho mediano ou  um pouco abaixo da média"
                },
                "medicine": {
                    "id": 3,
                    "description": "Desempenho muito acima da média"
                }
            },
            "geocode": {
                "id": 2706,
                "latitude": "-23.6099946498864",
                "longitude": "-46.7057347297655"
            }
        },
        {
            "id": 1332,
            "name": "UBS MENINOPOLIS MARIO FRANCISCO NAPOLITANO",
            "codCnes": 2027380,
            "phone": "1150961058",
            "address": {
                "id": 1319,
                "street": "RUA OSCAR GOMES CARDIM",
                "neighborhood": "VL CORDEIRO",
                "city": {
                    "id": 169,
                    "code": 355030,
                    "name": "São Paulo"
                }
            },
            "score": {
                "id": 31,
                "structureSize": {
                    "id": 1,
                    "description": "Desempenho acima da média"
                },
                "adaptationSenior": {
                    "id": 2,
                    "description": "Desempenho mediano ou  um pouco abaixo da média"
                },
                "medicalEquipment": {
                    "id": 1,
                    "description": "Desempenho mediano ou  um pouco abaixo da média"
                },
                "medicine": {
                    "id": 1,
                    "description": "Desempenho acima da média"
                }
            },
            "geocode": {
                "id": 1317,
                "latitude": "-23.6208951473229",
                "longitude": "-46.6876888275133"
            }
        },
        {
            "id": 23901,
            "name": "UBS JOSE DE BARROS MAGALDI",
            "codCnes": 2752301,
            "phone": "1131686571",
            "address": {
                "id": 22455,
                "street": "RUA SALVADOR CARDOSO",
                "neighborhood": "ITAIM BIBI",
                "city": {
                    "id": 169,
                    "code": 355030,
                    "name": "São Paulo"
                }
            },
            "score": {
                "id": 30,
                "structureSize": {
                    "id": 3,
                    "description": "Desempenho muito acima da média"
                },
                "adaptationSenior": {
                    "id": 3,
                    "description": "Desempenho acima da média"
                },
                "medicalEquipment": {
                    "id": 1,
                    "description": "Desempenho mediano ou  um pouco abaixo da média"
                },
                "medicine": {
                    "id": 1,
                    "description": "Desempenho acima da média"
                }
            },
            "geocode": {
                "id": 22833,
                "latitude": "-23.5873460769646",
                "longitude": "-46.6855430603014"
            }
        }
    ],
    "page": 0,
    "size": 3,
    "totalElements": 37685,
    "totalPages": 12562,
    "last": false
}
```
