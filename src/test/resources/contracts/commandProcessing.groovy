package contracts
import  org.springframework.cloud.contract.spec.Contract

[
        Contract.make {
            request {
                method 'POST'
                url('/commands')
                headers {
                    contentType(applicationJson())
                }
                body(file('data/commands_request.json'))
            }
            response {
                status OK()
                body(file('data/command_response.json'))
                headers {
                    contentType('application/json')
                }
            }
        },
        Contract.make {
            request {
                method 'POST'
                url('/commands')
                headers {
                    contentType(applicationJson())
                }
                body(file('data/commands_request_2.json'))
            }
            response {
                status OK()
                body(file('data/command_response_2.json'))
                headers {
                    contentType('application/json')
                }
            }
        },
        Contract.make {
            request {
                method 'POST'
                url('/commands')
                headers {
                    contentType(applicationJson())
                }
                body(file('data/commands_request_3.json'))
            }
            response {
                status OK()
                body(file('data/command_response_3.json'))
                headers {
                    contentType('application/json')
                }
            }
        }

]