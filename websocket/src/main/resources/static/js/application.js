var module = angular.module('app', [ 'ngRoute' ]);
/**
 * Configure routing
 */
module.config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'table.html',
		controller : 'table'
	}).otherwise('/');
});
/**
 * Factory to access event resource
 */
module.factory('EventFactory', function($log, $http, $q){
	return {
		/**
		 * Read all Events
		 */
		all:function(){
			var deferred = $q.defer();
		    var promise = deferred.promise;
		    $http.get('resource/events').success(function(data) {
		    	$log.info(data);
		    	deferred.resolve(data);
	          })
	          .error(function(error){
	        	  $log.error(error);
	            deferred.reject(error);
	          });
		    return promise;
		}
	}
});
/**
 * Service which provides Websocket access
 */
module.service("MessagingService", function($q, $timeout) {
    
    var service = {}, listener = $q.defer(), socket = {
      client: null,
      stomp: null
    }, messageIds = [];
    
    service.RECONNECT_TIMEOUT = 30000;
    service.SOCKET_URL = "/events";
    service.CHAT_TOPIC = "/topic/message";
    service.CHAT_BROKER = "/app/events";
    
    /**
     * Exposed receive method
     */
    service.receive = function() {
      return listener.promise;
    };
    
    /**
     * Reconnects if the connection was closed
     */
    var reconnect = function() {
      $timeout(function() {
        initialize();
      }, this.RECONNECT_TIMEOUT);
    };
    
    /**
     * Listener for new messages
     */
    var startListener = function() {
      socket.stomp.subscribe(service.CHAT_TOPIC, function(data) {
    	var message = JSON.parse(data.body)
        listener.notify(message);
      });
    };
    
    /**
     * Initializes the websocket connection.
     */
    var initialize = function() {
      socket.client = new SockJS(service.SOCKET_URL);
      socket.stomp = Stomp.over(socket.client);
      socket.stomp.connect({}, startListener);
      socket.stomp.onclose = reconnect;
    };
    
    initialize();
    return service;
  });
/**
 * Table controller
 */
module.controller('table', function($scope, EventFactory, MessagingService) {
	$scope.events = null;
	$scope.initalLoad = true;
	$scope.init = function(){
		EventFactory.all().then(
			function(data){
				$scope.events=data;
			});
	};
	$scope.init();
	MessagingService.receive().then(null, null, function(message) {
	  $scope.initalLoad = false;
	  $scope.events.push(message);
	});
	$scope.isLast = function(check){
		return (check && !$scope.initalLoad) ? 'danger' : null;
	}
});
