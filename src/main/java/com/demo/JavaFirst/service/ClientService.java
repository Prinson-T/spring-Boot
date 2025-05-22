    package com.demo.JavaFirst.service;

    import com.demo.JavaFirst.Exception.UserNotFound;
    import com.demo.JavaFirst.Exception.Validation;
    import com.demo.JavaFirst.entity.Employees;
    import com.demo.JavaFirst.entity.Sample;
    import com.demo.JavaFirst.model.ClientModel;
    import com.demo.JavaFirst.repository.ClientRepository;
    import jakarta.persistence.Id;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.ArrayList;
    import java.util.List;


    @Service
    public class ClientService {
        @Autowired
        ClientRepository clientRepository;



        public void register(ClientModel clientModel) {
            List<String> error=new ArrayList<>();
            if (clientModel.getName()==null || clientModel.getName().isEmpty()){
                error.add("name cannot br empty");

            }
            if (!error.isEmpty()){
                throw new Validation(error,"failed");
            }
            Sample sample = new Sample();
            sample.setName(clientModel.getName());
            sample.setEmail(clientModel.getEmail());
            sample.setPassword(clientModel.getPassword());
           clientRepository.save(sample );

        }

        public List<Sample> getList(){
            return clientRepository.findAll();

        }
       public Sample find(Long id){
            return clientRepository.findById(id).orElseThrow(()-> new UserNotFound("client not found "+ id));

       }

       public Sample update(ClientModel clientModel,Long id){
            List<String> error = new ArrayList<>();
            if (clientModel.getName() ==  null || clientModel.getName().isEmpty()){
                error.add("error");
            }
            int count = clientRepository.countById(id);
            if (count == 0){
                error.add("not found");

            }
            if (!error.isEmpty()){
                throw  new Validation(error,"error");
            }

            Sample sample = clientRepository.findById(id).orElseThrow(() -> new UserNotFound("clint not found "+id));
            sample.setName(clientModel.getName());
            sample.setPassword(clientModel.getPassword());
            sample.setEmail(clientModel.getEmail());
           return clientRepository.save(sample);

       }


        public void login(ClientModel clientModel) {
            List<String> error = new ArrayList<>();
            Sample sample=clientRepository.loginByIdAndPassword(clientModel.getEmail(),clientModel.getPassword());
            if (sample == null){
                error.add("invalid mailId or Password ");
            }
            if (!error.isEmpty()){
                throw  new Validation(error,"error");
            }
        }


        public List<Sample> searchAll(String name) {
                return clientRepository.findByAllData(name);
        }

        public void deleteById(Long id) {
            if (!clientRepository.existsById(id)){
                throw new UserNotFound("User not found with ID: " + id);
            }
            clientRepository.deleteById(id);
        }

    }
