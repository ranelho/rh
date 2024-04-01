# CRUD de Funcionários

Este README contém informações sobre os endpoints relacionados ao CRUD de funcionários.

## Endpoints

- `POST /v1/funcionarios`: Cria um novo funcionário.
- `GET /v1/funcionarios/{id}`: Retorna detalhes sobre um funcionário específico.
- `PATCH /v1/funcionarios/{id}`: Atualiza informações de um funcionário existente.
- `PATCH /v1/funcionarios/{id}/cargo/{idContrato}`: Atualiza o cargo de um funcionário.
- `GET /v1/funcionarios/nome/{nome}`: Retorna funcionários com um determinado nome.
- `GET /v1/funcionarios/matricula/{matricula}`: Retorna funcionários com uma determinada matrícula.
- `GET /v1/funcionarios/cpf/{cpf}`: Retorna funcionários com um determinado CPF.

## Exemplo de Uso (cURL) - "Todos os dados abaixo são ficticios"

### Criar um novo funcionário
```
curl -X 'POST' \
  'http://localhost:8080/rh/api/v1/funcionarios' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "nomeCompleto": "Catarina Vitória Aline Fernandes",
  "cpf": "56948296548",
  "dataNascimento": "1996-01-09",
  "rg": "249365194",
  "dataEmissaoRg": "2022-03-28",
  "ctps": "5694829/6548",
  "pis": "579.47959.09-6",
  "grauDeInstrucao": "ENSINO_MEDIO",
  "sexo": "FEMININO",
  "estadoCivil": "SOLTEIRO",
  "nomePai": "Jorge Hugo Manuel Fernandes",
  "nomeMae": "Eliane Rosa",
  "contatoRequest": {
    "email": "catarina_fernandes@santosferreira.adv.br",
    "telefone": "73982721273",
    "rua": "Rua do Bom Sossego",
    "bairro": "Doutor Gusmão",
    "cidade": "Eunápolis",
    "estado": "BA",
    "cep": "45821510",
    "numero": "514",
    "complemento": "",
    "pais": "Brasil"
  }
}'
```
### Obter detalhes sobre um funcionário específico
```
curl -X GET http://localhost:8080/v1/funcionarios/1
```
### Atualizar informações de um funcionário existente
```
curl -X PATCH http://localhost:8080/v1/funcionarios/1 -d '{"nome": "João Silva"}'
```
### Atualizar o cargo de um funcionário
```
curl -X 'PATCH' \
  'http://localhost:8080/rh/api/v1/funcionarios/{id}/cargo/1' \
  -H 'accept: */*'
```
### Obter funcionários com um determinado nome
```
curl -X 'GET' \
  'http://localhost:8080/rh/api/v1/funcionarios/nome/Mário' \
  -H 'accept: */*'
```
### Obter funcionários com uma determinada matrícula
```
curl -X 'GET' \
  'http://localhost:8080/rh/api/v1/funcionarios/matricula/411398' \
  -H 'accept: */*'
```
### Obter funcionários com um determinado CPF
```
curl -X 'GET' \
  'http://localhost:8080/rh/api/v1/funcionarios/cpf/56948296548' \
  -H 'accept: */*'
```
[Retornar ao README Principal](./README.md)