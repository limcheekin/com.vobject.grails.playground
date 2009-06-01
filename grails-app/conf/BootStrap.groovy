class BootStrap {
    def authenticateService

    def init = { servletContext ->
        //Adding Roles
        def roleAdmin = new Role(authority:'ROLE_ADMIN', description:'Admin User').save()
        def roleUser = new Role(authority:'ROLE_USER', description:'Application User').save()     
        def userAdmin = new User( username:'admin', userRealName:'Admin User 1', enabled: true, emailShow: true, email: 'admin@vobject.com', passwd: authenticateService.encodePassword('admin')).save()
        def user1 = new User(username:'user1', userRealName:'User 1', enabled: true, emailShow: true, email: 'user1@vobject.com', passwd: authenticateService.encodePassword('user1')).save()
        def secureUserEdit = new RequestMap(url: '/user/edit', configAttribute:'ROLE_ADMIN').save()
        def secureUserSave = new RequestMap(url: '/user/save', configAttribute:'ROLE_ADMIN').save()
        def secureUserCreate = new RequestMap(url: '/user/create', configAttribute:'ROLE_ADMIN').save()
        def secureUserList = new RequestMap(url: '/user/list', configAttribute:'ROLE_ADMIN, ROLE_USER').save()

        //Note that here we associate users with their respective roles
        roleAdmin.addToPeople(userAdmin)
        roleUser.addToPeople(userAdmin)
        roleUser.addToPeople(user1)        
    }
     
    def destroy = {
    }
} 
