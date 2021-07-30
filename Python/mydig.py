import dns.exception as de
import dns.message as dm
import dns.query as dq
import dns.name as dn
from datetime import datetime as dt

root = ['198.41.0.4','192.228.79.201',
        '192.33.4.12','199.7.91.13',
        '192.203.230.10','192.5.5.241',
        '192.112.36.4','198.97.190.53',
        '192.36.148.17','192.58.128.30',
        '193.0.14.129','199.7.83.42',
        '202.12.27.33']

# recursive function used to append items from data into a list
# if there is no list argument, then function creates a new empty list
# functions returns a list
def append_to_list(data,list = None):
    if(list == None):
        list = []
    for x in data:
        for y in x.items:
            list.append(str(y))
    return list

# recursive function used to create a dns query and output a dns response
def mydig_query(domain,rdtype,servers):

    # creates a variable called domain_name that converts text into a NAME object
    domain_name = dn.from_text(domain)

    # for loop to go through each server in the servers list
    for server in servers:

        # creates a variable called server that converts the ip_address,CNAME, or Name Server to string value
        server = str(server)
        try:

            # creates a variable called query that is set to a created dns query message using
            # the domain_name variable for query name and rdata type
            query = dm.make_query(domain_name, rdtype)

            # creates a variable called response that is set to a created response that
            # obtained after sending a query via udp
            response = dq.udp(query,server)

            # checks if the length of the answer section in response is greater than 0
            if len(response.answer) > 0:

                # for loop to go through each answer in the response answer section
                for ans in response.answer:

                    # checks if the rdata type of answer is 'A' which is equal to value of 1
                    # if true then returns response
                    if (ans.rdtype == 1):
                        return response

                    # if above condition is not true then checks if rdata type of answer is 'CNAME' which is equal to value of 5
                    # if true then returns two responses:
                    #   first response: response for inputted domain
                    #   second response: response for cname server that inputted domain was in
                    #       - second response returns the ip address of the cname server
                    elif(ans.rdtype == 5):
                        print(response)
                        print('')

                        # list called cname is created using the recursive function called append_to_list
                        # data is taken from answer section of response
                        cname = append_to_list(response.answer)

                        # creates a variable called digcname that is set to first value of the cname list
                        # first value of cname list is converted to a string
                        digcname = str(cname[0])

                        # the dns response is for searching the ip address for the cname server
                        secondresponse = mydig_query(digcname,rdtype,root)
                        return secondresponse

            # checks if the length of the additional section in response is greater than 0
            elif len(response.additional) > 0:

                #empty list called queried_ips
                queried_ips = []

                #for loop that goes through each server in the additional section of response
                for server in response.additional:

                    # checks if the rdata type of server is 'A' which is equal to value of 1
                    # if true then for loop condition
                    if server.rdtype == 1:

                        # for loop to go through each ip in server.items
                        for ip in server.items:

                            # each ip is then converted to a string value and the appended to the queried_ips list
                            queried_ips.append(str(ip))
                response = mydig_query(domain, rdtype, queried_ips)
                return response

            # checks if the length of the authority section in response is greater than 0
            elif len(response.authority) > 0:

                # list called queried_ns is created using the recursive function called append_to_list
                # data is taken from authority section of response
                queried_ns = append_to_list(response.authority)

                # for loop to go through each name server in the queried_ns list
                for ns in queried_ns:
                    response = mydig_query(ns, "A", root)

                    # list called queried_ips is created using the recursive function called append_to_list
                    # data is taken from answer section of response
                    queried_ips = append_to_list(response.answer)
                response = mydig_query(domain, rdtype, queried_ips)
                return response

        # Exception Errors
        except de.SyntaxError:
            print('Text input (Domain) is malformed')
        except de.Timeout:
            print('DNS operation (Query) timed out')
    return None, None

###### new line asking user to input name of domain they want to query
print('Enter Domain Name to Query: ')
###### new line asking user to input name of domain they want to query

# asks the user to input a text for the domain name he/she wants query
domain = input('')

# rdtype is set to A to resolve for the A record for a query
rdtype = 'A'

response = mydig_query(domain, rdtype, root)

# creates a variable called date_time that outputs the current local date and time
date_time = dt.now()

# prints the dns records of response
print(response)
print('')

# prints the query time of response
print('Query time: ' + '%.2f ms' % (response.time * 1000))

# prints the local current date and time
print('WHEN: ' + str(date_time))