import axios from "axios";

class UserService{

    getAllUsers(){
        const url = '/api/v1/credits/users';
        return axios.get(url);
    }

    deleteUser(userId) {
        const url = '/api/v1/users/' + userId;
        return axios.delete(url);
    }

    saveUserAndCreditDetail(user) {
        const url = '/api/v1/users/';
        return axios.post(url, user);
    }

    updateUser(id, user) {
        const url = '/api/v1/users/' + id;
        return axios.put(url, user);
    }

    getUserByTcNoAndBirthdate(tcno, birthdate) {
        const url = '/api/v1/credits/' + tcno + '/' + birthdate;
        return axios.get(url, tcno, birthdate);
    }
}

export default new UserService();