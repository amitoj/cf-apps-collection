Three blocks of IP addresses are reserved by the Internet Assigned Numbers Authority (IANA) for private Internets:
1. 10.0.0.0 - 10.255.255.255 (10/8 prefix)
2. 172.16.0.0 - 172.31.255.255 (172.16/12 prefix)
3. 192.168.0.0 - 192.168.255.255 (192.168/16 prefix)

RFC 1918
- 10.0.0.0/8
- 172.16.0.0/12
- 192.168.0.0/16

IP addresses out of this range are public addresses:

## CF SECURITY GROUPS - egress rules

restricting the flow of information outbound from one network to another.
 
public_networks
- 0.0.0.0-9.255.255.255
- 11.0.0.0-169.253.255.255
- 169.255.0.0-172.15.255.255
- 172.32.0.0-192.167.255.255
- 192.169.0.0-255.255.255.255

ASGs define allow rules, and their order of evaluation is unimportant when multiple ASGs apply to the same space or deployment.

Cloud Foundry blocks outgoing traffic to private IP range



## xyz